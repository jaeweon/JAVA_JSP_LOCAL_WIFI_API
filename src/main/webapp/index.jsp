
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page session="false" %>


<html>
<head>
    <title>index</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
          crossorigin="anonymous">
    <link rel="stylesheet" href="lib/index.css"/>


    <script src="https://code.jquery.com/jquery-3.6.1.min.js"
            integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
            crossorigin="anonymous"></script>


</head>
<script>
    $(document).ready(function(){
        //alert('페이지 로드!');
        $.ajax({
            url:'http://openapi.seoul.go.kr:8088/6446477159706f743735744d6b6d49/json/TbPublicWifiInfo/1/100/',
            type:'get',
            data:{},
            success:function(list){

                $.each(list.TbPublicWifiInfo.row,function(index, item){
                    $('#wifi-list').append(
                        '<tr>'+
                        '<th scope="col">-</th>'+  //거리
                        '<th scope="col">'+item.X_SWIFI_MGR_NO+'</th>'+   //관리번호
                        '<th scope="col">'+item.X_SWIFI_WRDOFC+'</th>'+   //자치구
                        '<th scope="col">'+item.X_SWIFI_MAIN_NM+'</th>'+  //와이파이명
                        '<th scope="col">'+item.X_SWIFI_ADRES1+'</th>'+   //도로명주소
                        '<th scope="col">'+X_SWIFI_ADRES2+'</th>'+   // 상세주소
                        '<th scope="col">'+item.X_SWIFI_ADRES2+'</th>'+  //설치위치(층)
                        '<th scope="col">'+item.X_SWIFI_INSTL_TY+'</th>'+   // 설치유형
                        '<th scope="col">'+item.X_SWIFI_INSTL_MBY+'</th>'+   //설치기관
                        '<th scope="col">'+item.X_SWIFI_SVC_SE+'</th>'+ // 서비스구분
                        '<th scope="col">'+item.X_SWIFI_CMCWR+'</th>'+ // 망종류
                        '<th scope="col">'+item.X_SWIFI_CNSTC_YEAR+'</th>'+ // 설치년도
                        '<th scope="col">'+item.X_SWIFI_INOUT_DOOR+'</th>'+ //실내외 구분
                        '<th scope="col">-</th>'+ // wifi 접속 현황 +item.X_SWIFI_REMARS3+
                        '<th scope="col">'+item.LAT+'</th>'+ // x좌표
                        '<th scope="col">'+item.LNT+'</th>'+ // y좌표
                        '<th scope="col">'+item.WORK_DTTM+'</th>'+  //작업일자
                        '</tr>'
                    );
                });


            },
            error:function(err){
                alert('네트워크 문제 발생');
            }
        });


    });
</script>

<body>

<h1>와이파이 정보 구하기</h1>
<div>
    <a href="http://127.0.0.1:8090/index2.jsp">홈</a>
    <span>|</span>
    <a href="http://127.0.0.1:8090/index2.jsp">위치 히스토리 목록</a>
    <span>|</span>
    <a href="load_api.jsp">Open API 와이파이 정보 가져오기</a>
</div>

<div style="margin-top:20px;">
    <span>LAT</span>
    <input/>
    ,
    <span>LNT</span>
    <input/>
    <button>내위치 가져오기</button>
    <button>근처 WIFI 정보보기</button>
    <button id="load-btn">데이터로드</button>
</div>


<table class="table" style="margin-top:30px;">
    <thead class="index-thead">
    <tr>
        <th scope="col">거리(km)</th>
        <th scope="col">관리번호</th>
        <th scope="col">자치구</th>
        <th scope="col">와이파이명</th>
        <th scope="col">도로명주소</th>
        <th scope="col">상세주소</th>
        <th scope="col">설치위치(층)</th>
        <th scope="col">설치유형</th>
        <th scope="col">설치기관</th>
        <th scope="col">서비스 구분</th>
        <th scope="col">망종류</th>
        <th scope="col">설치년도</th>
        <th scope="col">실내외구분</th>
        <th scope="col">WIFI 접속환경</th>
        <th scope="col">X좌표</th>
        <th scope="col">Y좌표</th>
        <th scope="col">작업일자</th>
    </tr>
    </thead>
    <tbody id="wifi-list">




    </tbody>
</table>
</body>
</html>
