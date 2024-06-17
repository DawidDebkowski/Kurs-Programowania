all: app

.PHONY:app
app:
	javac --module-path C:\\\"Program Files\"\\Java\\javafx-sdk-21.0.3\\lib --add-modules javafx.controls,javafx.fxml ./src/Main.java
	java --module-path C:\\\"Program Files\"\\Java\\javafx-sdk-21.0.3\\lib --add-modules javafx.controls,javafx.fxml Main

.PHONY:run
run:
	java app.Main

.PHONY:clean
clean:
	find . -name '*.class' -type f -delete