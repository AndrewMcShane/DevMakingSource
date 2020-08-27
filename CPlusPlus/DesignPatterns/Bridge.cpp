// Bridge pattern in C++

#include <string>
#include <iostream>

// Forward Decl:
class Model;

// Implementor class for the shader
class Shader
{
    public:
        virtual ~Shader() {};
        virtual void Render(Model* model) = 0;
};

// Abstraction class for the 3D model:
class Model
{
    public:
        Model(Shader* shader)
        : pShader(shader) 
        {};
        virtual ~Model() {};
        void SetShader(Shader* shader) { this->pShader = shader; }

        // Abstract Methods:
        virtual void Draw() = 0;
        virtual std::string GetName() = 0;

    protected:
        Shader* pShader;
};

// Shader example 1: unlit color shader:
class UnlitColorShader: public Shader
{
    public:
        UnlitColorShader(std::string color)
        : Shader(), color(color)
        {};
        ~UnlitColorShader() {};

        void Render(Model* model) override;
    protected:
        std::string color;
};

void UnlitColorShader::Render(Model* model)
{
    std::cout << "Rendering " << model->GetName() << " with a "
    << this->color << " color." << std::endl;
}

// Shader example 2: glass/transparent shader:
class GlassShader: public Shader
{
    public:
        GlassShader()
        : Shader()
        {};
        ~GlassShader() {};

        void Render(Model* model) override;
};

void GlassShader::Render(Model* model)
{
    std::cout << "Rendering " << model->GetName()
    << " to Look like glass." << std::endl;
}

// Model example 1: a cube:
class CubeModel: public Model
{
    public:
        CubeModel(Shader* shader)
        : Model(shader)
        {};
        ~CubeModel() {};

        void Draw() override
        {
            this->pShader->Render(this);
        }

        std::string GetName() override { return "Cube"; }
};

// Model example 2: a monkey:
class Suzanne: public Model
{
    public:
        Suzanne(Shader* shader)
        : Model(shader)
        {};
        ~Suzanne() {};

        void Draw() override
        {
            this->pShader->Render(this);
        }

        std::string GetName() override { return "Suzanne"; }
};

// Demo:
int main()
{
    // Create shaders:
    Shader* solidRed = new UnlitColorShader("red");
    Shader* solidBlue = new UnlitColorShader("blue");
    Shader* glass = new GlassShader();

    // Create the models:
    Model* cube = new CubeModel(solidRed);
    Model* monkey = new Suzanne(glass);

    // Draw the two models:
    cube->Draw(); // "Rendering Cube with a red color."
    monkey->Draw(); // "Rendering Suzanne to look like glass."

    // Switch out the shader on suzanne:
    monkey->SetShader(solidBlue);

    monkey->Draw(); // "Rendering Suzanne with a blue color."

    // Clean up:
    delete cube;
    delete monkey;
    
    delete glass;
    delete solidBlue;
    delete solidRed;
}