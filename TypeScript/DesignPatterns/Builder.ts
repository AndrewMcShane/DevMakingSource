// Builder example in TypeScript

// The Doctor Appointment class:
class DoctorAppointment
{
    public name: string;
    public primaryDoctorName: string;
    public time: Date;
    public visitReason: string;
    public pharmacyLocation: string;
    
    constructor(name: string, primaryDoctorName: string, time: Date, visitReason:string, pharmacyLocation: string)
    {
        this.name = name;
        this.primaryDoctorName = primaryDoctorName;
        this.time = time;
        this.visitReason = visitReason;
        this.pharmacyLocation = pharmacyLocation;
    }
}

// Appointment builder:
class AppointmentBuilder
{
    private name: string;
    private primaryDoctorName: string;
    private time: Date;
    private visitReason: string;
    private pharmacyLocation: string;

    public setName(name: string)
    {
        this.name = name;
    }

    public setPrimaryCare(name: string)
    {
        this.primaryDoctorName = name;
    }

    public setAppointmentTime(time: Date)
    {
        this.time = time;
    }

    public setVisitReason(visitReason: string)
    {
        this.visitReason = visitReason;
    }

    public setPharmacyLocation(location: string)
    {
        this.pharmacyLocation = location;
    }

    public build(): DoctorAppointment
    {
        return new DoctorAppointment(this.name, this.primaryDoctorName, this.time,
            this.visitReason, this.pharmacyLocation);
    }
}

// Demo
class BuilderDemo
{
    public execute()
    {
        let builder = new AppointmentBuilder();

        builder.setName("Clark Kent");
        // Set the appointment for Feb 18, 2003 at 12:30pm:
        builder.setAppointmentTime(new Date(2003, 2, 18, 12, 30));
        builder.setVisitReason("Meteor rock sickness.");
        builder.setPharmacyLocation("2001 main st, smallville, KS");
        builder.setPrimaryCare("Dr.Helen Bryce");

        // Create the appointment:
        let appointment = builder.build();

    }
}