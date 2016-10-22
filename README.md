# decisiontree-java
A program that learns a decision tree with the ID3 algorithm

# INPUT DATA

This program learns a decision tree for every input data.
	- The input file has to be a txt file
	- Every line is a training example with the same number of attributes
	- Columns are separated with a comma
	- The label of the examples are in the last column

# EXECUTING

To start the program, you have to open the command line tool and execute the program (in the folder „executable file“) with the following command:

	java -jar "LearnDecTree.jar" inputfile.txt outputfile.xml

With the given dataset of the cardata (car.txt) you only have to copy this command and execute it:

	java -jar "LearnDecTree.jar" car.txt tree.xml

After that you will find the xml file with the tree in the same directory.

# DESCRIPTION

We have created a fully commented javadoc to describe our solution. Generally we have used two packages. The package „algorithm“ contains all classes for loading the data, lern the tree and print it out to a file. The package „structure“ contains all classes for storing the several nodes and leafs in a useful structure.
