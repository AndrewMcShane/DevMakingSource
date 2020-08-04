// Builder demo in Java

// Our complex computer class that isn't too friendly to construct without some help:
public class Computer
{
    String computerName
    String cpu;
    String gpu;
    int gigsOfRam;
    boolean gigabitEthernet;
    boolean bonusPorts;
    boolean rgbLights;

    public Computer(String computerName, String cpuName, String gpuName, int gigsOfRam, 
    boolean hasGigabitEthernet, boolean hasBonusPorts, boolean hasRGBLights)
    {
        this.computerName = computerName;
        this.cpu = cpuName;
        this.gpu = gpuName;
        this.gigsOfRam = gigsOfRam;
        this.gigabitEthernet = hasGigabitEthernet;
        this.bonusPorts = hasBonusPorts;
        this.rgbLights = hasRGBLights;
    }

    public void PrintStats()
    {
        System.out.println("Specs for: " + this.computerName);
        System.out.println("CPU: " + this.cpu);
        System.out.println("GPU: " + this.gpu);
        System.out.println("RAM: " + this.gigsOfRam);
        System.out.println("Gigabit Ether: " + this.gigabitEthernet);
        System.out.println("Bonus Ports: " + this.bonusPorts);
        System.out.println("RGB Lights: " + this.rgbLights);
    }

}

// The builder class for helping build the computer:
public class ComputerBuilder
{
    // Copy the fields from Computer:
    String computerName
    String cpu;
    String gpu;
    int gigsOfRam;
    boolean gigabitEthernet;
    boolean bonusPorts;
    boolean rgbLights;

    // Set methods (with return statements for daisy-chaining):

    public ComputerBuilder SetName(String computerName)
    {
        this.computerName = computerName;
        return this;
    }

    public ComputerBuilder SetCPU(String cpuName)
    {
        this.cpu = cpuName;
        return this;
    }

    public ComputerBuilder SetGPU(String gpuName)
    {
        this.gpu = gpuName;
        return this;
    }

    public ComputerBuilder SetRamSize(int gigs)
    {
        this.gigsOfRam = gigs;
        return this;
    }

    public ComputerBuilder HasGigabitEthernet(boolean state)
    {
        this.gigabitEthernet = state;
        return this;
    }

    public ComputerBuilder HasBonusPorts(boolean state)
    [
        this.bonusPorts = state;
        return this;
    ]

    public ComputerBuilder HasRBGLights(boolean state)
    {
        this.rgbLights = state;
        return this;
    }

    // The builder method that returns a computer based on the parameters placed in:
    public Computer BuildComputer()
    {
        return new Computer(this.computerName, this.cpu, this.gpu, this.gigsOfRam, 
        this.gigabitEthernet, this.bonusPorts, this.rgbLights);
    }

}

// How we'd use our builder:
public class Solution
{
    public static void main(String[] args)
    {
        // Let's build a computational overlord:
        ComputerBuilder builder = new ComputerBuilder();

        builder.SetName("GlaDHAL 9001")
            .SetCPU("i9001 OctaCore 128GHz");
            .SetGPU("XRT 48910 2TB Dedicated Ram")
            .SetRamSize(16384)
            .HasGigabitEthernet(true)
            .HasBonusPorts(true)
            .HasRBGLights(true); 
            // RGB is more important to world domination than you'd think..

        // IT'S ALIVE!
        Computer theSingularity = builder.BuildComputer();
        theSingularity.PrintStats();
    }
}