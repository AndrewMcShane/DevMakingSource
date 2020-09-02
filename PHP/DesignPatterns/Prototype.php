<?php

// Prototype Pattern in PHP

// Basic Car Package interface:
interface CarPackage
{
    public function Clone(): CarPackage;
}

// Car implementation:
class SportsCarPackage implements CarPackage
{
    public $seatType;
    public $engineModel;
    public $hasSunRoof;

    public function __construct()
    {
        $this->seatType = "Basic";
        $this->engineModel = "V6";
        $this->hasSunRoof = false;
    }

    // Return a copy of the package:
    public function Clone(): CarPackage
    {
        $copy = new SportsCarPackage();
        $copy->seatType = $this->seatType;
        $copy->engineModel = $this->engineModel;
        $copy->hasSunRoof = $this->hasSunRoof;

        return $copy;
    }
}


// Car Package Catalogue:
class CarCatalogue
{
    private $catalogue;

    public function __construct()
    {
        $this->catalogue = array();
    }

    public function Add(string $key, CarPackage $prototype)
    {
        $this->catalogue[$key] = $prototype->Clone();
    }

    public function Get(string $key): CarPackage
    {
        return $this->catalogue[$key]->Clone();
    }
}


// Demo of the Prototype Pattern:


$catalogue = new CarCatalogue();

// Basic package with the default values:
$basicPackage = new SportsCarPackage();

$catalogue->Add("Basic", $basicPackage);

// Add a sportier option:
$trackPackage = new SportsCarPackage();

$trackPackage->seatType = "Leather";
$trackPackage->hasSunRoof = true;
$trackPackage->engineModel = "V6 Plus";

$catalogue->Add("Track", $trackPackage);

// Add the highest option:
$sportPackage = new SportsCarPackage();

$sportPackage->seatType = "Leather";
$sportPackage->hasSunRoof = true;
$sportPackage->engineModel = "V8";

$catalogue->Add("Sport", $sportPackage);

// Get a prototype package and put a more powerful engine in it!
$mySelectionPackage = $catalogue->Get("Sport");

$mySelectionPackage->engineModel = "V8 Hemi";