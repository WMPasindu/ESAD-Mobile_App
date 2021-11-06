package com.esad.group.assignment.two.dev.navigation;

import com.esad.group.assignment.two.dev.driver.DriverActivity;
import com.esad.group.assignment.two.dev.inspector.InspectorActivity;
import com.esad.group.assignment.two.dev.interfaces.SignInUserType;
import com.esad.group.assignment.two.dev.modal.AppUserSingleton;
import com.esad.group.assignment.two.dev.modal.LoginUser;
import com.esad.group.assignment.two.dev.passenger.PassengerActivity;
import com.esad.group.assignment.two.dev.transportManager.TransportManagerActivity;

public class NavigateUserScreen implements SignInUserType {
    // creating signin user types and its objects here -- adding more data to the existing objects
    @Override
    public Object selectedUserType(String type, LoginUser loginUser) {
        if (type.equalsIgnoreCase("Passenger")) {
            createUserObject("Michel", "Starc", "071-5831012", "931245678V", loginUser.getUsername(),
                    "https://196034-584727-raikfcquaxqncofqfm.stackpathdns.com/wp-content/uploads/2019/05/Business-Development-Manager-Profile-Photo.jpg");
            return PassengerActivity.class;
        } else if (type.equalsIgnoreCase("Driver")) {
            createUserObject("Mike", "Arthur", "071-1234012", "680227872V", loginUser.getUsername(),
                    "https://buffer.com/library/content/images/2020/05/Ash-Read.png");
            return DriverActivity.class;
        } else if (type.equalsIgnoreCase("Inspector")) {
            createUserObject("Mahela", "Jayawardena", "071-6790354", "841899608V", loginUser.getUsername(),
                    "https://196034-584727-raikfcquaxqncofqfm.stackpathdns.com/wp-content/uploads/2018/01/IT-QA-Analist-profile-picture-round.jpg");
            return InspectorActivity.class;
        } else if (type.equalsIgnoreCase("Transport Manager")) {
            createUserObject("Dasun", "Shanaka", "071-6790354", "921899608V", loginUser.getUsername(),
                    "https://pbs.twimg.com/media/Eny3RIRVQAMjKMc.jpg");
            return TransportManagerActivity.class;
        }else {
            return null;
        }
    }

    protected void createUserObject(String fname, String lname, String phone, String nic, String username, String profImage) {
        AppUserSingleton appUserSingleton = AppUserSingleton.getInstance();
        appUserSingleton.setUserId(1L);
        appUserSingleton.setFirstName(fname);
        appUserSingleton.setLastname(lname);
        appUserSingleton.setMobileNumber(phone);
        appUserSingleton.setNic(nic);
        appUserSingleton.setEmail(username);
        appUserSingleton.setProfileImage(profImage);
    }
}
