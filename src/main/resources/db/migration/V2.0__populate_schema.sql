insert into advisors
values (1, 'advisor1@gmail.com', 'advisor1', 'Alex', 'Roe', 'PARTNER'),
       (2, 'advisor2@gmail.com', 'advisor2', 'Sam', 'Ham', 'PARTNER'),
       (3, 'advisor3@gmail.com', 'advisor3', 'Alexa', 'Martines', 'ASSOCIATE'),
       (4, 'advisor4@gmail.com', 'advisor4', 'Arthur', 'Boo', 'PARTNER');


insert into applicants(id, email, user_name, first_name, last_name, ssn, city, street, zip, number,
                       apt)
values (1, 'applicant@gmail.com', 'applicant', 'Rick', 'Poor', '132143242', 'NYC', 'Lafayette Ave',
        '34567', 12, 345);

insert into applications(credit_amount_usd, status, applicant_id, advisor_id, created_at,
                         assigned_at)
values (30000, 'NEW', 1, NULL, '2022-09-30 15:37:01', NULL),
       (100000, 'ASSIGNED', 1, 1, '2022-09-30 15:37:01', '2022-09-30 16:37:01'),
       (50000, 'NEW', 1, NULL, '2022-09-20 15:37:01', NULL),
       (700000, 'NEW', 1, NULL, '2022-09-25 15:37:01', NULL),
       (300, 'NEW', 1, NULL, '2022-09-19 15:37:01', NULL);