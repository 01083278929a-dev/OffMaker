-- OffMaker DB Schema (MariaDB)
-- Source: user-provided DDLs
-- Charset/Collation: utf8mb4 / utf8mb4_general_ci

-- offmaker_db.TB_AUTH_HISTORY definition
CREATE TABLE `TB_AUTH_HISTORY` (
  `AUTH_SEQ` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '인증 시퀀스',
  `USER_NM` varchar(50) NOT NULL COMMENT '인증 요청 성명',
  `PHONE_NUM` varchar(20) NOT NULL COMMENT '인증 요청 번호',
  `AUTH_CODE` char(6) NOT NULL COMMENT '발송 인증번호',
  `TX_ID` varchar(100) DEFAULT NULL COMMENT '인증 대행사 거래 번호',
  `AUTH_STATUS` char(1) DEFAULT '0' COMMENT '상태 (0:요청, 1:성공, 2:실패)',
  `REQ_DTM` datetime DEFAULT current_timestamp(),
  `RES_DTM` datetime DEFAULT NULL COMMENT '인증 완료 일시',
  PRIMARY KEY (`AUTH_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='본인 인증 요청 이력';


-- offmaker_db.TB_USER_INFO definition
CREATE TABLE `TB_USER_INFO` (
  `USER_SEQ` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '사용자 시퀀스 키',
  `USER_NM` varchar(50) NOT NULL COMMENT '사용자 성명',
  `PHONE_NUM` varchar(20) NOT NULL COMMENT '휴대폰 번호 (본인인증 기반)',
  `USER_CI` varchar(255) DEFAULT NULL COMMENT '연계정보(CI) - 중복가입 방지용 고유키',
  `USER_DI` varchar(255) DEFAULT NULL COMMENT '중복가입확인정보(DI)',
  `BIRTH_DATE` date DEFAULT NULL COMMENT '생년월일',
  `GENDER` char(1) DEFAULT NULL COMMENT '성별 (M/F)',
  `USER_ROLE` varchar(50) DEFAULT 'REGISTERED NURSE' COMMENT '직무/역할',
  `PROFILE_IMG_PATH` varchar(255) DEFAULT NULL COMMENT '프로필 이미지 경로',
  `USER_STATUS` char(1) DEFAULT '1' COMMENT '상태 (1:활성, 0:탈퇴, 9:정지)',
  `REG_DTM` datetime DEFAULT current_timestamp() COMMENT '가입 일시',
  `UPD_DTM` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '수정 일시',
  PRIMARY KEY (`USER_SEQ`),
  UNIQUE KEY `PHONE_NUM` (`PHONE_NUM`),
  UNIQUE KEY `USER_CI` (`USER_CI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='사용자 기본 정보';


-- offmaker_db.TB_GROUP_MAIN definition
CREATE TABLE `TB_GROUP_MAIN` (
  `GROUP_SEQ` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '그룹 시퀀스',
  `GROUP_NM` varchar(100) NOT NULL COMMENT '그룹 명칭 (예: 71병동)',
  `GROUP_CODE` varchar(20) NOT NULL COMMENT '그룹 초대 코드 (링크 생성용 고유값)',
  `GROUP_INTRO` varchar(255) DEFAULT NULL COMMENT '그룹 소개글',
  `REG_USER_SEQ` bigint(20) NOT NULL COMMENT '생성자 (방장)',
  `REG_DTM` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`GROUP_SEQ`),
  UNIQUE KEY `GROUP_CODE` (`GROUP_CODE`),
  KEY `FK_GROUP_REG_USER` (`REG_USER_SEQ`),
  CONSTRAINT `FK_GROUP_REG_USER` FOREIGN KEY (`REG_USER_SEQ`) REFERENCES `TB_USER_INFO` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='병동 그룹 정보';


-- offmaker_db.TB_GROUP_MEMBER definition
CREATE TABLE `TB_GROUP_MEMBER` (
  `MEMBER_SEQ` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '멤버 매핑 시퀀스',
  `GROUP_SEQ` bigint(20) NOT NULL COMMENT '그룹 참조',
  `USER_SEQ` bigint(20) NOT NULL COMMENT '사용자 참조',
  `MEMBER_ROLE` char(1) DEFAULT '1' COMMENT '권한 (1:일반멤버, 9:관리자)',
  `MAIN_GROUP_YN` char(1) DEFAULT 'N' COMMENT '나의 메인 그룹 여부 (Y/N)',
  `JOIN_DTM` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`MEMBER_SEQ`),
  UNIQUE KEY `UK_GROUP_USER` (`GROUP_SEQ`,`USER_SEQ`),
  KEY `FK_MEMBER_USER` (`USER_SEQ`),
  CONSTRAINT `FK_MEMBER_GROUP` FOREIGN KEY (`GROUP_SEQ`) REFERENCES `TB_GROUP_MAIN` (`GROUP_SEQ`),
  CONSTRAINT `FK_MEMBER_USER` FOREIGN KEY (`USER_SEQ`) REFERENCES `TB_USER_INFO` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='그룹별 참여 멤버 관리';


-- offmaker_db.TB_SET_CALENDAR_SYNC definition
CREATE TABLE `TB_SET_CALENDAR_SYNC` (
  `SYNC_SEQ` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '연동 설정 시퀀스',
  `USER_SEQ` bigint(20) NOT NULL COMMENT '사용자 시퀀스 참조',
  `CALENDAR_NM` varchar(100) NOT NULL COMMENT '캘린더 이름',
  `SYNC_YN` char(1) DEFAULT 'Y' COMMENT '동기화 여부',
  `REG_DTM` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`SYNC_SEQ`),
  KEY `FK_SYNC_USER_SEQ` (`USER_SEQ`),
  CONSTRAINT `FK_SYNC_USER_SEQ` FOREIGN KEY (`USER_SEQ`) REFERENCES `TB_USER_INFO` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='캘린더 연동 설정';


-- offmaker_db.TB_SET_DUTY_TYPE definition
CREATE TABLE `TB_SET_DUTY_TYPE` (
  `DUTY_TYPE_SEQ` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '근무 유형 시퀀스',
  `USER_SEQ` bigint(20) NOT NULL COMMENT '사용자 시퀀스 참조',
  `DUTY_NM` varchar(50) NOT NULL COMMENT '근무 명칭 (데이, 이브닝 등)',
  `START_TIME` time DEFAULT NULL COMMENT '근무 시작 시간',
  `END_TIME` time DEFAULT NULL COMMENT '근무 종료 시간',
  `COLOR_CODE` char(7) DEFAULT NULL COMMENT '표시 색상 (Hex Code)',
  `DUTY_CATEGORY` varchar(20) DEFAULT NULL COMMENT '카테고리 (일반, 오프, 휴가 등)',
  `OFF_DEDUCT_YN` char(1) DEFAULT 'N' COMMENT '연차 차감 여부',
  `USE_YN` char(1) DEFAULT 'Y' COMMENT '사용 여부',
  `REG_DTM` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`DUTY_TYPE_SEQ`),
  KEY `FK_DUTY_USER_SEQ` (`USER_SEQ`),
  CONSTRAINT `FK_DUTY_USER_SEQ` FOREIGN KEY (`USER_SEQ`) REFERENCES `TB_USER_INFO` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='근무 유형 설정';


-- offmaker_db.TB_SET_VACATION_MGMT definition
CREATE TABLE `TB_SET_VACATION_MGMT` (
  `VAC_SEQ` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '휴가 관리 시퀀스',
  `USER_SEQ` bigint(20) NOT NULL COMMENT '사용자 시퀀스 참조',
  `BASE_YEAR` char(4) NOT NULL COMMENT '해당 연도 (YYYY)',
  `TOTAL_DAYS` decimal(5,1) DEFAULT 0.0 COMMENT '총 부여 일수 (반차 고려)',
  `USED_DAYS` decimal(5,1) DEFAULT 0.0 COMMENT '사용 일수',
  `REMAIN_DAYS` decimal(5,1) DEFAULT 0.0 COMMENT '잔여 일수',
  `REG_DTM` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`VAC_SEQ`),
  UNIQUE KEY `UK_VAC_USER_YEAR` (`USER_SEQ`,`BASE_YEAR`),
  CONSTRAINT `FK_VAC_USER_SEQ` FOREIGN KEY (`USER_SEQ`) REFERENCES `TB_USER_INFO` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='연도별 휴가 일수 관리';


-- offmaker_db.TB_USER_ACCOUNT definition
CREATE TABLE `TB_USER_ACCOUNT` (
  `ACC_SEQ` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '계정 시퀀스',
  `USER_SEQ` bigint(20) NOT NULL COMMENT '사용자 시퀀스 참조',
  `LOGIN_ID` varchar(50) NOT NULL COMMENT '로그인 ID (휴대폰 번호 등)',
  `LOGIN_PW` varchar(255) DEFAULT NULL COMMENT '암호화된 비밀번호 (소셜전용은 NULL 가능)',
  `REG_DTM` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`ACC_SEQ`),
  UNIQUE KEY `LOGIN_ID` (`LOGIN_ID`),
  KEY `FK_ACC_USER_SEQ` (`USER_SEQ`),
  CONSTRAINT `FK_ACC_USER_SEQ` FOREIGN KEY (`USER_SEQ`) REFERENCES `TB_USER_INFO` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='사용자 로그인 계정';


-- offmaker_db.TB_USER_SOCIAL definition
CREATE TABLE `TB_USER_SOCIAL` (
  `SOCIAL_SEQ` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '소셜 연동 시퀀스',
  `USER_SEQ` bigint(20) NOT NULL COMMENT '사용자 시퀀스 참조',
  `SOCIAL_TYPE` varchar(20) NOT NULL COMMENT '소셜 타입 (KAKAO, NAVER, APPLE, GOOGLE)',
  `SOCIAL_ID` varchar(255) NOT NULL COMMENT '소셜 제공업체 고유 식별자',
  `SOCIAL_EMAIL` varchar(100) DEFAULT NULL COMMENT '소셜 계정 이메일',
  `REG_DTM` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`SOCIAL_SEQ`),
  UNIQUE KEY `UK_SOCIAL_TYPE_ID` (`SOCIAL_TYPE`,`SOCIAL_ID`),
  KEY `FK_SOCIAL_USER_SEQ` (`USER_SEQ`),
  CONSTRAINT `FK_SOCIAL_USER_SEQ` FOREIGN KEY (`USER_SEQ`) REFERENCES `TB_USER_INFO` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='소셜 로그인 연동 정보';


-- offmaker_db.TB_USER_TERMS_AGREE definition
CREATE TABLE `TB_USER_TERMS_AGREE` (
  `AGREE_SEQ` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '동의 이력 시퀀스',
  `USER_SEQ` bigint(20) NOT NULL COMMENT '사용자 시퀀스 참조',
  `SERVICE_TERMS_YN` char(1) NOT NULL COMMENT '서비스 이용약관 동의 (Y/N)',
  `PRIVACY_POLICY_YN` char(1) NOT NULL COMMENT '개인정보 처리방침 동의 (Y/N)',
  `MARKETING_RECV_YN` char(1) NOT NULL COMMENT '마케팅 정보 수신 동의 (Y/N)',
  `AGREE_DTM` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`AGREE_SEQ`),
  KEY `FK_TERMS_USER_SEQ` (`USER_SEQ`),
  CONSTRAINT `FK_TERMS_USER_SEQ` FOREIGN KEY (`USER_SEQ`) REFERENCES `TB_USER_INFO` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='약관 동의 이력';


-- offmaker_db.TB_DUTY_SCHED definition
CREATE TABLE `TB_DUTY_SCHED` (
  `SCHED_SEQ` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '스케줄 시퀀스',
  `USER_SEQ` bigint(20) NOT NULL COMMENT '사용자 시퀀스 참조',
  `DUTY_DATE` date NOT NULL COMMENT '근무 날짜',
  `DUTY_TYPE_SEQ` bigint(20) NOT NULL COMMENT '근무 유형(D, E, N, Off 등) 참조',
  `MEMO` text DEFAULT NULL COMMENT '해당 날짜 메모',
  `REG_DTM` datetime DEFAULT current_timestamp(),
  `UPD_DTM` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`SCHED_SEQ`),
  UNIQUE KEY `UK_USER_DATE` (`USER_SEQ`,`DUTY_DATE`),
  KEY `FK_SCHED_DUTY_TYPE` (`DUTY_TYPE_SEQ`),
  CONSTRAINT `FK_SCHED_DUTY_TYPE` FOREIGN KEY (`DUTY_TYPE_SEQ`) REFERENCES `TB_SET_DUTY_TYPE` (`DUTY_TYPE_SEQ`),
  CONSTRAINT `FK_SCHED_USER_SEQ` FOREIGN KEY (`USER_SEQ`) REFERENCES `TB_USER_INFO` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='사용자별 개인 근무 스케줄';


-- offmaker_db.TB_SET_ALARM_CONFIG definition
CREATE TABLE `TB_SET_ALARM_CONFIG` (
  `ALARM_SEQ` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '알림 설정 시퀀스',
  `USER_SEQ` bigint(20) NOT NULL COMMENT '사용자 시퀀스 참조',
  `DUTY_TYPE_SEQ` bigint(20) NOT NULL COMMENT '근무 유형 참조',
  `ALARM_YN` char(1) DEFAULT 'Y' COMMENT '알림 활성화 (Y/N)',
  `BEFORE_MIN` int(11) DEFAULT NULL COMMENT '시작 몇 분 전 알림',
  `ALARM_SOUND_PATH` varchar(255) DEFAULT NULL COMMENT '알림음 경로',
  `REG_DTM` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`ALARM_SEQ`),
  KEY `FK_ALARM_USER_SEQ` (`USER_SEQ`),
  KEY `FK_ALARM_DUTY_SEQ` (`DUTY_TYPE_SEQ`),
  CONSTRAINT `FK_ALARM_DUTY_SEQ` FOREIGN KEY (`DUTY_TYPE_SEQ`) REFERENCES `TB_SET_DUTY_TYPE` (`DUTY_TYPE_SEQ`),
  CONSTRAINT `FK_ALARM_USER_SEQ` FOREIGN KEY (`USER_SEQ`) REFERENCES `TB_USER_INFO` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='근무별 알림 설정';


-- offmaker_db.TB_SET_CALENDAR_BAR definition
CREATE TABLE `TB_SET_CALENDAR_BAR` (
  `BAR_SEQ` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '바 설정 시퀀스',
  `USER_SEQ` bigint(20) NOT NULL COMMENT '사용자 시퀀스 참조',
  `DUTY_TYPE_SEQ` bigint(20) NOT NULL COMMENT '연결된 근무 유형 참조',
  `DISP_ORDER` int(11) DEFAULT 0 COMMENT '화면 표시 순서',
  `REG_DTM` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`BAR_SEQ`),
  KEY `FK_BAR_USER_SEQ` (`USER_SEQ`),
  KEY `FK_BAR_DUTY_TYPE` (`DUTY_TYPE_SEQ`),
  CONSTRAINT `FK_BAR_DUTY_TYPE` FOREIGN KEY (`DUTY_TYPE_SEQ`) REFERENCES `TB_SET_DUTY_TYPE` (`DUTY_TYPE_SEQ`),
  CONSTRAINT `FK_BAR_USER_SEQ` FOREIGN KEY (`USER_SEQ`) REFERENCES `TB_USER_INFO` (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='캘린더 퀵 근무 설정 바';

