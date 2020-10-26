insert into users (login, password, phone_number) values
   ('login1','password1', '12343')
,  ('login2','password2', '12344');

insert into cards(dtype, balance, card_number, currency, limit_, used_limit, user_id) VALUES
 ('CreditCard', 1000, '5264', 'RUB', 0, 0 , 1);

INSERT INTO payments (card_number, pay_sum, paymentuid, status, user_id) VALUES
( '5264', 10, 'paymentuid1', 'SUCCESS', 1);

