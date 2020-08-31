// Flyweight pattern in C#

using System;
using System.Collections.Generic;
using System.Collections;

public interface ISprite 
{
    void Draw(int x, int y);
}

// Base class for a texture:
public class Texture
{
    string source;
    public Texture(string source)
    {
        this.source = source;
    }

}

// Enum class for faster accessing:
public enum TextureName
{
    Grass,
    Path,

}

// Implementation of a sprite:
public class Sprite: ISprite
{
    // The flyweight:
    Texture texture;

    public Sprite(Texture texture)
    {
        this.texture = texture;
    }

    public Sprite(TextureName name)
    {
        this.texture = TextureManager.GetTexture(name);
    }

    public void SetTexture(TextureName name)
    {
        this.texture = TextureManager.GetTexture(name);
    }

    public void Draw(int x, int y)
    {
        Console.WriteLine($"Drawing sprite at {x}, {y}");
    }
}

public class TextureManager
{
    static Dictionary<TextureName, Texture> cachedTextures = new Dictionary<TextureName, Texture>();

    public static void Add(TextureName name, string src)
    {
        Texture tex = new Texture(src);
        cachedTextures.Add(name, tex);
    }

    public static Texture GetTexture(TextureName name)
    {
        Texture tex = null;
        cachedTextures.TryGetValue(name, out tex);

        return tex;
    }
}


public class Solution
{
    public static void Main(string[] args)
    {
        string grassSrc = "Grass.png";
        string pathSrc = "Path.png";

        // Add into the flyweight:
        TextureManager.Add(TextureName.Grass, grassSrc);
        TextureManager.Add(TextureName.Path, pathSrc);

        // Create two sprites using the Texture Name:
        Sprite grassSprite = new Sprite(TextureName.Grass);
        Sprite pathSprite = new Sprite(TextureName.Path);

        // Create a sprite using a Texture reference:
        Texture grass = TextureManager.GetTexture(TextureName.Grass);
        Sprite anotherSprite = new Sprite(grass);

        // Draw the sprites:

        grassSprite.Draw(0, 1);  // Drawing sprite at 0, 1
        pathSprite.Draw(0, 2); // Drawing sprite at 0, 2

        anotherSprite.Draw(0, 3); // Drawing sprite at 0, 3

        // Swap the sprite out:
        anotherSprite.SetTexture(TextureName.Path);

        anotherSprite.Draw(0, 4); // Drawing sprite at 0, 4
    }
}