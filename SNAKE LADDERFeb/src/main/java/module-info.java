module com.example.snakeladderfeb {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.snakeladderfeb to javafx.fxml;
    exports com.example.snakeladderfeb;
}