# MultiSourceDataFusion
NJU iip kjb MutiSourceDataFusion

## Java Maven 创建 fx程序 

### 编译问题

1. java编译的过程默认不会将资源文件加入到编译中，IDEA创建的maven项目，编译后的文件保存在target中。

   fxml，css等文件未被加入到编译，如果希望通过

   ```java
   getClass().getResource()
   ```

   来加载资源，必须保证这些文件加入编译，出现在target中

   可以在pom.xml中添加编译的配置类似如下

   ```xml
   <build>
       <resources>
         <resource>
           <directory>src/main/java</directory>
           <includes>
             <include>**/*.fxml</include>
             <include>**/*.css</include>
             <include>**/*.png</include>
           </includes>
           <filtering>true</filtering>
         </resource>
       </resources>
   </build>
   ```




### Controller变量命名统一

1. 每一个组件的命名尽量按照比如

   一个账号存在 TextField 的controller中，取所有的大写字母以tf作为前缀

   比如Button这种只有一个大写的，可以以比较通用的格式如btn作为前缀

   ```java
   private TextField account;
   private Button btnLogin;
   ```

   如果是fxml中文件定义的变量请按照正确的前缀命名

   ```java
   	@FXML
       private Label LblMainTitle;
       @FXML
       private AnchorPane APLoadData;
       @FXML
       private AnchorPane APParticiple;
       @FXML
       private AnchorPane APEntity;
       @FXML
       private AnchorPane APSetting;
       @FXML
       private BorderPane BPMainViewPane;
   ```

   如果是辅助的定义的变量
   就根据普通的驼峰命名法命名

   ```java
   private AnchorPane currentMenuPane;
   private AnchorPane connectionConfigPane;
   private List<MenuModule> menuModules;
   ```


### 直接在代码中添加控件的方式

```java
public class Login extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Welcome");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 6);
        grid.setColumnSpan(actiontarget, 2);
        grid.setHalignment(actiontarget, RIGHT);
        actiontarget.setId("actiontarget");
		btn.setOnAction(e -> {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Sign in button pressed");
            }
        });
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Sign in button pressed");
            }
        });

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
```



### Reference

1. 登录界面的参考：https://blog.csdn.net/legendnovo/article/details/10555941
