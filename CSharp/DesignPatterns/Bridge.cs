// Bridge pattern in C#
using System;

// Implementor class for a shader:
public interface Shader
{
    // Abstract operation needing to be fulfilled:
    void Render(Model model);
}

// Abstraction class for a 3D model:
public abstract class Model
{
    // Implementor Reference:
    protected Shader shader;
    
    /* 
        In a real scenario, 
        we'd want some triangle data
        and some vert data.
    */

    public Model(Shader shader)
    {
        this.shader = shader;
    }

    public void SetShader(Shader shader)
    {
        this.shader = shader;
    }

    // Draws the model to the screen:
    public abstract void Draw();

    // For example purposes, we'll want to show a name:
    public abstract string GetName();
}


// Shader example 1: unlit color shader:
public class UnlitColorShader: Shader
{
    // Shader color property:
    string color;

    public UnlitColorShader(string color)
    {
        this.color = color;
    }

    // Implement the render method:
    public void Render(Model model)
    {
        Console.WriteLine("Rendering " + model.GetName() 
        + " with a " + this.color + " color.");
    }
}

// Shader example 2: a glass/transparent shader:
public class GlassShader: Shader
{
    public GlassShader() {}

    // implementing the render method:
    public void Render(Model model)
    {
        Console.WriteLine("Rendering " + model.GetName() 
        + " to look like glass.");
    }
}


// Model example 1: A basic cube:
public class CubeModel: Model
{
    public CubeModel(Shader shader) : base(shader) {}

    public override void Draw()
    {
        this.shader.Render(this);
    }

    public override string GetName()
    {
        return "Cube";
    }
}

// Model example 2: a monkey:
// (Suzanne, which you might recognize from Blender!)
public class Suzanne: Model
{
    public Suzanne(Shader shader) : base(shader) {}

    public override void Draw()
    {
        this.shader.Render(this);
    }

    public override string GetName()
    {
        return "Suzanne";
    }
}


// Demo of the Bridge pattern:
public class Solution
{
    public static void Main(string[] args)
    {
        // Create shaders:
        Shader solidRed = new UnlitColorShader("red");
        Shader solidBlue = new UnlitColorShader("blue");
        Shader glass = new GlassShader();

        // Create the models:
        Model cube = new CubeModel(solidRed);
        Model monkey = new Suzanne(glass);

        // Draw the two models:
        cube.Draw(); // "Rendering Cube with a red color."
        monkey.Draw(); // "Rendering Suzanne to look like glass."

        // Switch out the shader on suzanne:
        monkey.SetShader(solidBlue);

        monkey.Draw(); // "Rendering Suzanne with a blue color."

    }
}