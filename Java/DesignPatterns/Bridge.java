//Bridge pattern in Java

//Implementor:
public interface Theme
{
    String Background();
    String Foreground();
    String LineColor();
    String FontColor();
    String AltFontColor();
}

//Abstraction:
public abstract class UIObject
{
    protected Theme theme;

    public UIObject(Theme theme)
    {
        this.theme = theme;
    }

    public abstract void Draw();
}

//Theme 1: LightMode:
public class LightMode implements Theme
{
    public String Background() { return "#ffffff"; }
    public String Foreground() { return "#aaaaaa"; }
    public String LineColor() { return "#0c60cd"; }
    public String FontColor() { return "#222222"; }
    public String AltFontColor() { return "#000000"; }
}

//Theme 2: Dark Mode:
public class DarkMode implements Theme
{
    public String Background() { return "#111111"; }
    public String Foreground() { return "#2d2d2d"; }
    public String LineColor() { return "#0c60cd"; }
    public String FontColor() { return "#dfdfdf"; }
    public String AltFontColor() { return "#efefef"; }
}


//UI Object 1: a button:
public class UIButton extends UIObject
{
    public UIButton(Theme theme)
    {
        super(theme);
    }

    public void Draw() 
    {
        System.out.println("Drawing a button on the screen.");
        System.out.println("\tText Color: " + this.theme.FontColor());
        System.out.println("\tButton Color: " + this.theme.Background());
        System.out.println("\tHighlight Text Color: " + this.theme.AltFontColor());
    }
}

//UI Object 2: a graph:
public class UIGraph extends UIObject
{
    public UIGraph(Theme theme)
    {
        super(theme);
    }

    public void Draw()
    {
        System.out.println("Drawing a graph on the screen.");
        System.out.println("\tMain Text Color: " + this.theme.FontColor());
        System.out.println("\tLine Color: " + this.theme.LineColor());
        System.out.println("\tAxis Text Color: " + this.theme.AltFontColor());
        System.out.println("\tGraph Background Color: " + this.theme.Foreground());
    }
}

public class Solution
{
    public static void main(String[] args)
    {
        // Create the themes:
        Theme light = new LightMode();
        Theme dark = new DarkMode();

        // Create the UI Objects:
        UIObject button = new UIButton(light);
        UIObject graph = new UIGraph(dark);

        // Draw the objects:
        button.Draw();
        graph.Draw();
    }
}

//Output:
/*
    Drawing a button on the screen.
        Text Color: #222222
        Button Color: #ffffff
        Highlight Text Color: #000000
        
    Drawing a graph on the screen.
        Main Text Color: #dfdfdf
        Line Color: #0c60cd
        Axis Text Color: #efefef
        Graph Background Color: #2d2d2d
*/