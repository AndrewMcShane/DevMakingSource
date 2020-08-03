<?php

    class Singleton
    {
        // The static instance:
        private static $instance;
        // Instance data:
        private $logEnabled;

        // Private construction:
        private final function __construct()
        {
            $this->logEnabled = false;
        }

        // Private instance getter:
        private static function GetInstance()
        {
            if(!isset(self::$instance))
            {
                self::$instance = new Singleton();
            }
            return self::$instance;
        }

        // Public static methods for accessing the singleton class:

        // In this example, we'll be implementing a basic logging system:
        public static function EnableLogging($state)
        {
            $logger = self::GetInstance();

            $logger->logEnabled = $state;
        }

        public static function LogMessage($message)
        {
            $logger = self::GetInstance();

            if($logger->logEnabled == true)
            {
                echo $message;
            }
        }
    }