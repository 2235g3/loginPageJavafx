module com.company.loginpagejavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.company.loginpagejavafx to javafx.fxml;
    exports com.company.loginpagejavafx;
}