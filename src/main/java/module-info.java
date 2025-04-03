module hi.verkefni.vidmot.flaskumottaka {
    requires javafx.controls;
    requires javafx.fxml;


    opens hi.verkefni.vidmot.flaskumottaka to javafx.fxml;
    exports hi.verkefni.vidmot.flaskumottaka;
}