# Bridge Pattern in Python

# In other examples we'd create base classes for the shader and model.
# Since we don't need them in Python, we'll go straight into it!

# Shader 1: Unlit color shader:
class UnlitColorShader(object):
    def __init__(self, color):
        self.color = color
    def render(self, model):
        print("Rendering {} with a {} color".format(model.getName(), self.color))

# Shader 2: Glass shader:
class GlassShader(object):
    def render(self, model):
        print("Rendering {} to look like glass.".format(model.getName()))
    
# Model 1: A Cube:
class CubeModel(object):
    def __init__(self, shader):
        self.shader = shader
    
    def draw(self):
        self.shader.render(self)
    
    def getName(self):
        return "Cube"

# Model 2: A Monkey:
class Suzanne(object):
    def __init__(self, shader):
        self.shader = shader
    
    def draw(self):
        self.shader.render(self)
    
    def getName(self):
        return "Suzanne"

# Demo:


# Create shaders:
solidRed = UnlitColorShader("red")
solidBlue = UnlitColorShader("blue")
glass = GlassShader()

# Create the models:
cube = CubeModel(solidRed)
monkey = Suzanne(glass)

# Draw the two models:
cube.draw() # "Rendering Cube with a red color."
monkey.draw() # "Rendering Suzanne to look like glass."

# Switch out the shader on suzanne:
monkey.shader = solidBlue

monkey.draw()  # "Rendering Suzanne with a blue color."