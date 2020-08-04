// Builder demo in C++

// Base class:
#include <iostream>
class Contact
{
    public:
        Contact(const char* name, const char* nickname, const char* address,
        const char* phoneNumber, const bool favorite)
        : name(name), nickname(nickname), address(address), 
            phoneNumber(phoneNumber), favorite(favorite)
        {
        };

        void PrintContact()
        {
            printf("Contact information for : %s\n", this->name);
            printf("\tNickname: %s\n", this->nickname);
            printf("\tAddress: %s\n", this->address);
            printf("\tPhone Number: %s\n", this->phoneNumber);
            printf("\tIs Favorite: %s\n", this->favorite? "Yes" : "No");
        }

    protected:
        const char* name;
        const char* nickname;
        const char* address;
        const char* phoneNumber;
        bool favorite;
};


// Builder class:
class ContactBuilder
{
    public:
        ContactBuilder& SetName(const char* name) { 
            this->name = name; 
            return *this; 
        }
        ContactBuilder& SetNickname(const char* name) { 
            this->nickname = name; 
            return *this; 
        }
        ContactBuilder& SetAddress(const char* address) {
            this->address = address;
            return *this;
        }
        ContactBuilder& SetPhoneNumber(const char* phoneNumber) {
            this->phoneNumber = phoneNumber;
            return *this;
        }    
        ContactBuilder& SetAsFavorite(const bool favorite) {
            this->favorite = favorite;
            return *this;
        }

        Contact* BuildContact()
        {
            return new Contact(this->name, this->nickname, this->address,
            this->phoneNumber, this->favorite);
        }

    protected:
        const char* name;
        const char* nickname;
        const char* address;
        const char* phoneNumber;
        bool favorite;
    
};

// Demo:

int main()
{
    // Create the contact:
    ContactBuilder builder;
    builder.SetName("Harvey")
    .SetNickname("Harv")
    .SetAddress("445 N Gotham Pkwy")
    .SetPhoneNumber("555-454-1920")
    .SetAsFavorite(true);

    // Build:
    Contact* pContact = builder.BuildContact();

    // Get info:
    pContact->PrintContact();

    // Clean up:
    delete pContact;
}