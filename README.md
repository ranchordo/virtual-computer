# virtual-computer
This program simulates an Intel 74181 by simualating the logic gates, but it has attached memory. There is an instruction set that can be loaded into instMatrix, a variable inside the program.
# Instruction set
instMatrix is a matrix. Each row is 7 bits long. The first 3 bits represent the instruction, and the last 4 represent the parameter.
Below is a list of the 8 instructions, where the parameter (0-16) is p.

0 - memMatrix[0] + memMatrix[1] --> memMatrix[2]

1 - memMatrix[0] - memMatrix[1] --> memMatrix[2]

2 - memMatrix[p] --> memMatrix[15]

3 - memMatrix[15] --> memMatrix[p]

4 - printASCIICharater(memMatrix[p] CONCAT memMatrix[p+1])

5 - GetKeyboardInput --> memMatrix[p]

6 - p --> memMatrix[2]

7 - No Operation
