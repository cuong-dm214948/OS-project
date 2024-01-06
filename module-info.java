module Javafx {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.annotation;
	
	opens application to javafx.graphics, javafx.fxml;

	exports producerconsumer;
	    // other module declarations
	
}
