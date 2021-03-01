BIN_DIR := bin
JAR_DIR := jar/ChodaiTower2_orig

all:
	make -C $(BIN_DIR) all
	make -C $(BIN_DIR) jar

clean:
	rm -rf $(BIN_DIR)/save.dat $(BIN_DIR)/chTower $(JAR_DIR)/ChodaiTower2.jar
