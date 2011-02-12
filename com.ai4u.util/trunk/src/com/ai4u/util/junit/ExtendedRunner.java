package com.ai4u.util.junit;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.Test.None;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.internal.runners.model.ReflectiveCallable;
import org.junit.internal.runners.statements.*;
import org.junit.rules.*;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;
import org.junit.runners.model.*;

import com.ai4u.util.AnnotationUtils;

public class ExtendedRunner extends Runner {

	private final Class<?> testClass;
	private RunnerScheduler fScheduler = new RunnerScheduler() {
		public void schedule(Runnable childStatement) {
			childStatement.run();
		}

		public void finished() {
			// do nothing
		}
	};

	/**
	 * Constructs a new {@code ParentRunner} that will run {@code @TestClass}
	 * 
	 * @throws InitializationError
	 */
	public ExtendedRunner(Class<?> testClass) throws InitializationError {
		this.testClass = testClass;
		validate();
	}

	@Override
	public Description getDescription() {
		Description description = Description.createSuiteDescription(getName(),
				testClass.getAnnotations());
		for (Method child : getChildren()) {
			description.addChild(describeChild(child));
		}
		return description;
	}

	@Override
	public void run(RunNotifier notifier) {
		EachTestNotifier testNotifier = new EachTestNotifier(notifier,
				getDescription());
		try {
			Statement statement = classBlock(notifier);
			statement.evaluate();
		} catch (AssumptionViolatedException e) {
			testNotifier.fireTestIgnored();
		} catch (StoppedByUserException e) {
			throw e;
		} catch (Throwable e) {
			testNotifier.addFailure(e);
		}
	}

	private String getName() {
		return testClass.getName();
	}

	private void validate() throws InitializationError {
		List<Throwable> errors = new ArrayList<Throwable>();
		collectInitializationErrors(errors);
		if (!errors.isEmpty())
			throw new InitializationError(errors);
	}

	private void collectInitializationErrors(List<Throwable> errors) {
		validatePublicVoidNoArgMethods(BeforeClass.class, true, errors);
		validatePublicVoidNoArgMethods(AfterClass.class, true, errors);
	}

	private void validatePublicVoidNoArgMethods(
			Class<? extends Annotation> annotation, boolean isStatic,
			List<Throwable> errors) {
		List<Method> methods = AnnotationUtils.getAnnotatedMethods(testClass,
				annotation);

		for (Method method : methods) {
			validatePublicVoidNoArg(method, isStatic, errors);
		}
	}

	public void validatePublicVoidNoArg(Method method, boolean isStatic,
			List<Throwable> errors) {
		validatePublicVoid(method, isStatic, errors);
		if (method.getParameterTypes().length != 0)
			errors.add(new Exception("Method " + method.getName()
					+ " should have no parameters"));
	}

	public void validatePublicVoid(Method method, boolean isStatic,
			List<Throwable> errors) {
		if (Modifier.isStatic(method.getModifiers()) != isStatic) {
			String state = isStatic ? "should" : "should not";
			errors.add(new Exception("Method " + method.getName() + "() "
					+ state + " be static"));
		}
		if (!Modifier.isPublic(method.getDeclaringClass().getModifiers()))
			errors.add(new Exception("Class "
					+ method.getDeclaringClass().getName()
					+ " should be public"));
		if (!Modifier.isPublic(method.getModifiers()))
			errors.add(new Exception("Method " + method.getName()
					+ "() should be public"));
		if (method.getReturnType() != Void.TYPE)
			errors.add(new Exception("Method " + method.getName()
					+ "() should be void"));
	}

	private List<Method> getChildren() {
		return AnnotationUtils.getAnnotatedMethods(testClass, Test.class);
	}

	private Description describeChild(Method method) {
		if (method.getAnnotation(Repeat.class) != null) {
			int times = method.getAnnotation(Repeat.class).value();

			Description description = Description.createSuiteDescription(
					testName(method) + " [" + times + " times]",
					method.getAnnotations());

			for (int i = 1; i <= times; i++) {
				description.addChild(Description.createTestDescription(
						testClass, "[" + i + "] " + method.getName()));
			}
			return description;
		}
		return Description.createTestDescription(testClass, testName(method),
				method.getAnnotations());
	}

	private String testName(Method method) {
		return method.getName();
	}

	private Statement classBlock(final RunNotifier notifier) {
		Statement statement = childrenInvoker(notifier);
		statement = withBeforeClasses(statement);
		statement = withAfterClasses(statement);
		statement = withClassRules(statement);
		return statement;
	}

	private Statement childrenInvoker(final RunNotifier notifier) {
		return new Statement() {
			@Override
			public void evaluate() {
				runChildren(notifier);
			}
		};
	}

	private void runChildren(final RunNotifier notifier) {
		for (final Method each : getChildren()) {
			fScheduler.schedule(new Runnable() {
				public void run() {
					ExtendedRunner.this.runChild(each, notifier);
				}
			});
		}
		fScheduler.finished();
	}

	private void runChild(final Method method, RunNotifier notifier) {
		Description description = describeChild(method);
		if (method.getAnnotation(Ignore.class) != null) {
			notifier.fireTestIgnored(description);
		} else if (method.getAnnotation(Repeat.class) != null) {
			runRepeatedly(methodBlock(method), description, notifier);
		} else {
			runLeaf(methodBlock(method), description, notifier);
		}
	}

	private Statement methodBlock(Method method) {
		Object test;
		try {
			test = new ReflectiveCallable() {
				@Override
				protected Object runReflectiveCall() throws Throwable {
					return createTest();
				}
			}.run();
		} catch (Throwable e) {
			return new Fail(e);
		}

		Statement statement = methodInvoker(method, test);
		statement = possiblyExpectingExceptions(method, test, statement);
		statement = withPotentialTimeout(method, test, statement);
		statement = withBefores(method, test, statement);
		statement = withAfters(method, test, statement);
		statement = withRules(method, test, statement);
		return statement;
	}

	private Object createTest() throws Exception {
		return testClass.getConstructor().newInstance();
	}

	private Statement methodInvoker(final Method method, final Object test) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				invokeExplosively(method, test);
			}
		};
	}

	private Statement possiblyExpectingExceptions(Method method, Object test,
			Statement next) {
		Test annotation = method.getAnnotation(Test.class);
		return expectsException(annotation) ? new ExpectException(next,
				getExpectedException(annotation)) : next;
	}

	private Statement withPotentialTimeout(Method method, Object test,
			Statement next) {
		long timeout = getTimeout(method.getAnnotation(Test.class));
		return timeout > 0 ? new FailOnTimeout(next, timeout) : next;
	}

	private Statement withBefores(Method method, final Object target,
			final Statement statement) {
		final List<Method> befores = AnnotationUtils.getAnnotatedMethods(
				testClass, Before.class);
		return befores.isEmpty() ? statement : new Statement() {
			@Override
			public void evaluate() throws Throwable {
				for (Method before : befores) {
					invokeExplosively(before, target);
				}
				statement.evaluate();
			}
		};
	}

	private Statement withAfters(Method method, final Object target,
			final Statement statement) {
		final List<Method> afters = AnnotationUtils.getAnnotatedMethods(
				testClass, After.class);
		return afters.isEmpty() ? statement : new Statement() {
			@Override
			public void evaluate() throws Throwable {
				List<Throwable> errors = new ArrayList<Throwable>();
				errors.clear();
				try {
					statement.evaluate();
				} catch (Throwable e) {
					errors.add(e);
				} finally {
					for (Method each : afters)
						try {
							invokeExplosively(each, target);
						} catch (Throwable e) {
							errors.add(e);
						}
				}
				MultipleFailureException.assertEmpty(errors);
			}
		};
	}

	private Statement withRules(Method method, Object target,
			Statement statement) {
		Statement result = statement;
		result = withMethodRules(method, target, result);
		result = withTestRules(method, target, result);
		return result;
	}

	private Statement withMethodRules(Method method, Object target,
			Statement result) {
		List<TestRule> testRules = getTestRules(target);
		for (MethodRule each : getMethodRules(target))
			if (!testRules.contains(each))
				result = each.apply(result, new FrameworkMethod(method), target);
		return result;
	}

	private Statement withTestRules(Method method, Object target,
			Statement statement) {
		List<TestRule> testRules = getTestRules(target);
		return testRules.isEmpty() ? statement : new RunRules(statement,
				testRules, describeChild(method));
	}

	private List<TestRule> getTestRules(Object target) {
		return AnnotationUtils.getAnnotatedFieldsValues(testClass, target,
				Rule.class, TestRule.class);
	}

	private List<MethodRule> getMethodRules(Object target) {
		return AnnotationUtils.getAnnotatedFieldsValues(testClass, target,
				Rule.class, MethodRule.class);
	}

	private Statement withBeforeClasses(final Statement statement) {
		final List<Method> befores = AnnotationUtils.getAnnotatedMethods(
				testClass, BeforeClass.class);
		return befores.isEmpty() ? statement : new Statement() {
			@Override
			public void evaluate() throws Throwable {
				for (Method before : befores) {
					invokeExplosively(before, null);
				}
				statement.evaluate();
			}
		};
	}

	private Statement withAfterClasses(final Statement statement) {
		final List<Method> afters = AnnotationUtils.getAnnotatedMethods(
				testClass, AfterClass.class);
		return afters.isEmpty() ? statement : new Statement() {
			@Override
			public void evaluate() throws Throwable {
				List<Throwable> errors = new ArrayList<Throwable>();
				errors.clear();
				try {
					statement.evaluate();
				} catch (Throwable e) {
					errors.add(e);
				} finally {
					for (Method each : afters)
						try {
							invokeExplosively(each, null);
						} catch (Throwable e) {
							errors.add(e);
						}
				}
				MultipleFailureException.assertEmpty(errors);
			}
		};
	}

	private Statement withClassRules(Statement statement) {
		List<TestRule> classRules = classRules();
		return classRules.isEmpty() ? statement : new RunRules(statement,
				classRules, getDescription());
	}

	private List<TestRule> classRules() {
		List<TestRule> results = new ArrayList<TestRule>();
		for (Field field : classRuleFields())
			results.add(getClassRule(field));
		return results;
	}

	private List<Field> classRuleFields() {
		return AnnotationUtils.getAnnotatedFields(testClass, ClassRule.class);
	}

	private TestRule getClassRule(final Field field) {
		try {
			return (TestRule) field.get(null);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"How did getAnnotatedFields return a field we couldn't access?");
		}
	}

	private final void runRepeatedly(Statement statement, Description description,
			RunNotifier notifier) {
		for (Description desc : description.getChildren()) {
			EachTestNotifier eachNotifier = new EachTestNotifier(notifier,
					desc);
			
			eachNotifier.fireTestStarted();
			try {
				statement.evaluate();
			} catch (AssumptionViolatedException e) {
				eachNotifier.addFailedAssumption(e);
			} catch (Throwable e) {
				eachNotifier.addFailure(e);
			} finally {
				eachNotifier.fireTestFinished();
			}
		}
	}
	
	private final void runLeaf(Statement statement, Description description,
			RunNotifier notifier) {
		EachTestNotifier eachNotifier = new EachTestNotifier(notifier,
				description);
		eachNotifier.fireTestStarted();
		try {
			statement.evaluate();
		} catch (AssumptionViolatedException e) {
			eachNotifier.addFailedAssumption(e);
		} catch (Throwable e) {
			eachNotifier.addFailure(e);
		} finally {
			eachNotifier.fireTestFinished();
		}
	}

	private boolean expectsException(Test annotation) {
		return getExpectedException(annotation) != null;
	}

	private Class<? extends Throwable> getExpectedException(Test annotation) {
		if (annotation == null || annotation.expected() == None.class)
			return null;
		else
			return annotation.expected();
	}

	private long getTimeout(Test annotation) {
		if (annotation == null) {
			return 0;
		}
		return annotation.timeout();
	}

	private void invokeExplosively(final Method method, final Object test)
			throws Throwable {
		new ReflectiveCallable() {
			@Override
			protected Object runReflectiveCall() throws Throwable {
				return method.invoke(test);
			}
		}.run();
	}

}
