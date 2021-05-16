SRC=src
CLASSES_DEST=bin/classes
DIST=dist
DOC=doc
MANIFEST=MANIFEST.MF

PACKAGE_RACINE=fr/unicaen/l2/info/prog/puzzle
PACKAGE_MODEL=$(PACKAGE_RACINE)/model
PACKAGE_VIEW_CONTROLLER=$(PACKAGE_RACINE)/view_controller

all: compile docs getjar

compile:
	javac -classpath $(CLASSES_DEST) -sourcepath $(SRC) -d $(CLASSES_DEST) $(SRC)/$(PACKAGE_RACINE)/Main.java

docs:
	javadoc -classpath $(CLASSES_DEST) -sourcepath $(SRC) -d $(DOC) -subpackages $(subst /,.,$(PACKAGE_RACINE))

run:
	java -classpath $(CLASSES_DEST) $(subst /,.,$(PACKAGE_RACINE)).Main
# We can also use "java -jar $(DIST)/puzzleGame.jar"

getjar:
	cd bin/classes; jar cfm puzzleGame.jar ../../$(MANIFEST) fr/
	rm -rf $(DIST)
	mkdir $(DIST)
	mv bin/classes/puzzleGame.jar $(DIST)

clean:
	rm -rf $(CLASSES_DEST)/*
	rm -rf $(DOC)/*
	rm -rf $(DIST)
