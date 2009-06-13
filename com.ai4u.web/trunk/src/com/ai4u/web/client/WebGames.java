package com.ai4u.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebGames implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button ticTacToeButton = new Button("Tic Tac Toe");

		// Add the ticTacToe Button to the RootPanel
		RootPanel.get("ticTacToeButtonContainer").add(ticTacToeButton);

		// Focus the cursor on the first button when the app loads
		ticTacToeButton.setFocus(true);

		// Create a handler for the tic tac toe button
		class ticTacToeHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				ticTacToeButton.setEnabled(false);
			}
		}

		// Add a handler to send the name to the server
		ticTacToeHandler handler = new ticTacToeHandler();
		ticTacToeButton.addClickHandler(handler);
	}
}
