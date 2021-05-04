# 자동차 경주 게임
## 진행 방법
* 숫자 야구 게임 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 과제를 제출한다.

## 과제 제출 과정
* [과제 제출 방법](https://github.com/next-step/nextstep-docs/tree/master/precourse)

## 구현대상 소스
### [UI]
* CarRacingApplication : 자동차 경주 진행을 위한 UI App
* InputView : 자동차경주 실행에 필요한 참가자명, 라운드 횟수 입력을 위한 입력기능 UI View
* OutputView : 자동차경주 실행결과 출력을 위한 출력기능 UI View
### [Presentation Layer]
* CarRacingController : 자동차경주 실행 및 결과조회를 위한 API 진입소스
* CarRacingRequest : UI의 사용자 입력값을 Backend 로 전달하기 위한 입력 dto
* CarRacingResponse : 자동차 경주 실행결과정보를 Frontend 로 전달하기 위한 출력 dto
### [Service Layer]
* CarRacingService : 자동차 경주 참가자 등록 및 경주실행을 위한 서비스 클래스
### [Domain Layer]
* CarRacing : 참가자와 경주 자동차의 매핑 정보를 저장하는 일급컬렉션 도메인 클래스. 자동차 경주 자체를 표현하기 위한 도메인 오브젝트.
* Participants : 경주 참가자들을 표현하기 위한 일급컬렉션 클래스.
* Participant : 경주 참가자 한 명을 표현하기 위한 도메인 클래스.
* RacingCar : 자동차 경주에 참가한 자동차 하나를 표현하기 위한 도메인 클래스.
* DriveStrategy : 자동차 이동에 대한 전략을 정의한 전략 인터페이스.
* RandomDriveEngine : 자동차 이동여부를 랜덤하게 결정해주는 DriveStrategy 의 구현 클래스.
* RacingResult : 라운드별 결과정보와 우승자 정보를 저장하고 있는 도메인 클래스.
* RoundResult : 라운드별 결과정보를 표현하기 위한 dto 클래스
* RoundScore : 라운드별 참가자와 자동차 위치를 표현하기 위한 경기 성적정보 dto 클래스
### [common utils]
* StringUtils : 문자열 공백검증을 위한 유틸 클래스.