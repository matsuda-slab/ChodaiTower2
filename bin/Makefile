BIN_DIR := ../bin
SRC_DIR := ../src/chTower
JAR_DIR := ../jar/ChodaiTower2_orig
DEBUG_DIR := ../jar/ChodaiTower2_debug

all:
	javac -d $(BIN_DIR) $(SRC_DIR)/character/*.java $(SRC_DIR)/event/*.java $(SRC_DIR)/main/*.java -encoding UTF-8

jar:
	jar cvfm $(JAR_DIR)/ChodaiTower2.jar $(JAR_DIR)/../mf.txt chTower/character/*  chTower/event/* chTower/main/*

debug:
	jar cvfm $(DEBUG_DIR)/ChodaiTower2_debug.jar $(DEBUG_DIR)/../mf.txt chTower/character/*  chTower/event/* chTower/main/*

play:
	@java -jar $(JAR_DIR)/ChodaiTower2.jar
