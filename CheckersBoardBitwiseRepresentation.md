# Introduction #

For better performance, I chose to represent the board using integers where each tool is represented by a lit bit instead of an object.
This way calculations are made with binary operations instead of loops.

# Details #

The checkers board is built out of 64 cells, where only 32 of them are active (can be placed upon).
An integer (which is a 32 bit variable) can represent a single sort of tools where each lit bit means a tool of the kind is placed in the bit's index.
Moves can be implemented using logical shifts and so on.