# pinmarket 결매시스템을 결합한 물물교환 사이트 - 하일호

## 사용 기술 스택 : java, jQuery, spring framework, BootStrap, mybatis, oracle

## 1.소개 
##### 프로젝트 목적
스프링의 전반적인 기능을 활용하여 평소에 생각만 하고 있던 콘텐츠를 직접 만들어보고 싶어서 해당 프로젝트를 진행하였습니다.

##### 프로젝트 내용
물건을 경매에 올려 물건을 사고 파는것이 아닌 직접 만나 물물교환을 하도록 도와주는 사이트입니다.

##### 주요 업무
ui기획, db모델링, 요구사항 명세서, Back/Front까지 혼자 개발해보았습니다.

## 2.개발환경 및 설계
##### 개발환경
language : java 1.8, javaScript, HTML5/CSS
FrameWork : Sprign Framework, BootStrap
DataBase : Oracle
Other : Git Hub, SourceTree, Notion, ovenApp.io, diagrams

##### ui 설계
https://ovenapp.io/view/Ntd1KnyhgbNzXo9tqhX7V03tHz7taMpm/bpi5I

##### db 설계
<img width="658" alt="db설계" src="https://user-images.githubusercontent.com/78103044/184116011-e81768d7-786e-418f-ad37-c122840b1254.PNG">

## 3.구조 및 핵심 공통 모듈
##### 사용자 와이어프레임
<img width="937" alt="사용자 와이어프레임" src="https://user-images.githubusercontent.com/78103044/184116594-c8372e68-4f23-47a9-bd0d-a577da7e9bdc.PNG">
##### 관리자 와이어프레임
<img width="847" alt="관리자 와이어프레임" src="https://user-images.githubusercontent.com/78103044/184116617-0d268dc6-4e3a-4041-be9c-aa2b00c99144.PNG">

##### 핵심 공통 모듈
**interceptor**
- 로그인 전용 사이트로 세션이 성립되지 않은 상태의 유저 접근을 차단했습니다.

**tiles**
 - 페이지를 header, body, aside, footer, layout으로 모듈화 했습니다.

**aop**
 - 핵심 서비스인 옥션과 상품결제 페이지에서 예외 발생 시 발생한 예외 메시지를 날짜별로 파일에 로깅 할 때 사용했습니다.

**transaction**
 - 옥션 게시글과 랭크 상품의 관계 성립 시 그리고 결제 진행 과정에서 주문 데이터를 추가할 때 설정했습니다.

**scheduler**
 - 매일 00시에 옥션의 기간을 현재 날짜와 비교하여 유효하면 진행 중, 유효하지 않다면 마감으로 변경되게 설정했습니다.

**예외처리**
 - @ControllerAdvice 어노테이션을 이용하여 404, 500등의 예외처리를 진행했습니다.

## 4.핵심 기능
##### 네이버 아이디 로그인 api 사용
##### 당근 마켓과 유사한 위치 기반 경매 시스템
##### 경매완료 시 경매등록자와 경매참여자간의 채팅방 생성
##### BootPay를 이용하여 nicepay pg사를 연동해 결정
