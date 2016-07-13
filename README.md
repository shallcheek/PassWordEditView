# PasswordEditView
PasswordEditView


##Attributes

name     | format  |java code
--- | ---   |---           |---
edit_background | color\|reference |   public void setBackground(int background)
edit_line_color    | color\|reference   | public void setLineColor(int lineColor)
edit_line_focus_color     | color\|reference   |    public void setLineFocusColor(int lineFocusColor)
edit_circle_color     | color\|reference |public void setCircleColor(int circleColor)
edit_circle_focus_color     |  color\|reference      |  public void setCircleFocusColor(int circleFocusColor)
edit_background_radius     | integer    | public void setDirectionType(int direction)
edit_circle_size   |       dimension\|integer |     public void setCircleSize(int circleSize)
edit_max_length  | integer    |     public void setMaxLen(int password_max_len)



##XML
```xml
       <com.chaek.android.passwordeditview.PassWordEditView
        android:layout_width="match_parent"
        android:layout_height="40dp"
        app:edit_background="#ffF5F7F9"
        app:edit_background_radius="3dp"
        app:edit_circle_color="#fa760a"
        app:edit_circle_size="5dp"
        app:edit_line_color="#ffE1E4E8"
        app:edit_line_focus_color="#ffBEC5CD"
        app:edit_max_length="6" />
```
##Demo


<img src="./img/1.png" width="40%"><img>

<img src="./img/2.png" width="40%"><img>
 
 
