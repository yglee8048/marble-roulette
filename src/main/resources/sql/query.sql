INSERT INTO `team` (`name`, `leader_id`)
VALUES ('1팀', 'louis.m'),
       ('2팀', 'mason.k'),
       ('3팀', 'tigger.lee'),
       ('4팀', 'hana.zn');

INSERT INTO `member` (`id`, `name`, `team_name`)
VALUES ('tigger.lee', '티거', '3팀'),
       ('mason.kim', '메이슨', '2팀'),
       ('louis.m', '루이스', '1팀'),
       ('louis.o', '루이', '2팀'),
       ('hana.zn', '하나', '4팀'),
       ('eddie.tang', '에디', '2팀'),
       ('luffy.hee', '루피', '1팀');

INSERT INTO `game`(`title`, `description`, `score`, `type`, `image`, `time`)
VALUES ('깃발 꾸미기', '팀원들과 깃발을 꾸미며 친해져 봐요!', 10, 'TEAM', 'color-pen.png', 5),
       ('OX 퀴즈', '당신의 지성을 뽐내세요!', 50, 'PERSONAL', 'xo-game.png', 20),
       ('판 뒤집기', '치열한 판 뒤집기 전쟁, 다쳐도 몰라요.', 20, 'TEAM', 'color-board.png', 30);