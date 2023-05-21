-- Clients --
insert into clients(full_name, email)
values ('Bob', 'bob@test.com');
insert into clients(full_name, email)
values ('Carl', 'carl@test.com');
insert into clients(full_name, email)
values ('Peter', 'peter@test.com');
insert into clients(full_name, email)
values ('Annie', 'annie@test.com');
insert into clients(full_name, email)
values ('Garry', 'garry@test.com');
-- Accounts --
-- Bob Accounts --
insert into accounts(account_name, account_number, balance, client_id)
values ('1stBobAcc', 111111, 10000, 1); -- 1
insert into accounts(account_name, account_number, balance, client_id)
values ('2ndBobAcc', 121212, 500, 1); -- 2
insert into accounts(account_name, account_number, balance, client_id)
values ('3thBobAcc', 131313, 7898, 1);
-- 3
-- Carl Accounts --
insert into accounts(account_name, account_number, balance, client_id)
values ('1stCarlAcc', 222222, 6000, 2); -- 4
insert into accounts(account_name, account_number, balance, client_id)
values ('2stCarlAcc', 212121, 2000, 2);
-- 5
-- Peter Accounts --
insert into accounts(account_name, account_number, balance, client_id)
values ('1stPeterAcc', 333333, 10070, 3); -- 6
insert into accounts(account_name, account_number, balance, client_id)
values ('2ndPeterAcc', 313131, 3000, 3); -- 7
insert into accounts(account_name, account_number, balance, client_id)
values ('3thPeterAcc', 323232, 2431, 3); -- 8
insert into accounts(account_name, account_number, balance, client_id)
values ('4thPeterAcc', 353535, 6785, 3);
-- 9
-- Annie Accounts --
insert into accounts(account_name, account_number, balance, client_id)
values ('1stAnnieAcc', 414141, 100000, 4);
-- 10
-- Garry Accounts --
insert into accounts(account_name, account_number, balance, client_id)
values ('1stGarryAcc', 515151, 40987, 5); -- 11
insert into accounts(account_name, account_number, balance, client_id)
values ('2ndGarryAcc', 525252, 4532, 5); -- 12
insert into accounts(account_name, account_number, balance, client_id)
values ('3thGarryAcc', 535353, 12563, 5);
-- 13
-- Operations --
-- from Bob to Carl --
insert into credit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('CREDIT', '2021-04-12', 4580, 5, 1);
insert into debit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('DEBIT', '2021-04-12', 4580, 5, 1);
insert into credit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('CREDIT', '2021-04-13', 200, 4, 2);
insert into debit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('DEBIT', '2021-04-13', 200, 4, 2);
-- from Carl to Bob --
insert into credit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('CREDIT', '2022-01-14', 300, 3, 4);
insert into debit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('DEBIT', '2022-01-14', 300, 3, 4);
-- from Bob to Peter --
insert into credit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('CREDIT', '2023-01-05', 1000, 8, 2);
insert into debit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('DEBIT', '2023-01-05', 1000, 8, 2);
insert into credit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('CREDIT', '2023-03-05', 600, 6, 1);
insert into debit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('DEBIT', '2023-03-05', 600, 6, 1);
insert into credit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('CREDIT', '2023-07-27', 10000, 9, 3);
insert into debit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('DEBIT', '2023-07-27', 10000, 9, 3);
-- from Peter to Bob --
insert into credit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('CREDIT', '2023-05-23', 4550, 1, 9);
insert into debit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('DEBIT', '2023-05-23', 4550, 1, 9);
insert into credit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('CREDIT', '2023-07-10', 1245, 3, 7);
insert into debit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('DEBIT', '2023-07-10', 1245, 3, 7);
insert into credit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('CREDIT', '2023-04-05', 8756, 2, 6);
insert into debit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('DEBIT', '2023-04-05', 8756, 2, 6);
-- from Bob to Annie --
insert into credit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('CREDIT', '2019-01-01', 100000, 10, 1);
insert into debit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('DEBIT', '2019-01-01', 100000, 10, 1);
-- from Bob to Garry --
insert into credit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('CREDIT', '2023-01-14', 100, 11, 1);
insert into debit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('DEBIT', '2023-01-14', 100, 11, 1);
insert into credit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('CREDIT', '2023-06-10', 400, 13, 3);
insert into debit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('DEBIT', '2023-06-10', 400, 13, 3);
insert into credit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('CREDIT', '2023-11-20', 805, 12, 2);
insert into debit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('DEBIT', '2023-11-20', 805, 12, 2);
-- from Garry to Bob --
insert into credit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('CREDIT', '2023-01-14', 150, 1, 11);
insert into debit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('DEBIT', '2023-01-14', 150, 1, 11);
insert into credit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('CREDIT', '2023-06-10', 500, 2, 12);
insert into debit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('DEBIT', '2023-06-10', 500, 2, 12);
insert into credit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('CREDIT', '2023-11-20', 1000, 3, 13);
insert into debit_operation(category, operation_date, amount, recipient_account_id, sender_account_id)
values ('DEBIT', '2023-11-20', 1000, 3, 13);