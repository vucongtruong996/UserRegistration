INSERT INTO EVENT (EVENT ,organiser ) VALUES ('Book Fair', 'National Book Foundation');
INSERT INTO REGISTRATION (NAME ,CNIC ,EVENT_ID ) VALUES ('ali', '7777777777', 1);
INSERT INTO FAMILY_MEMBERS_LIST (REGISTRATION_ID, FAMILY_MEMBERS_LIST) VALUES
    ((SELECT REGISTRATION_ID FROM REGISTRATION WHERE name = 'ali'), '1234567890123'),
    ((SELECT REGISTRATION_ID FROM REGISTRATION WHERE name = 'ali'), '1234567890123');