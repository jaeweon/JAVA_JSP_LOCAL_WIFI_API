
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page session="false" %>
<%@ page import="com.NearWifiService" %>
<%@ page import="com.NearWifi" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>index</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
          crossorigin="anonymous">
    <link rel="stylesheet" href="index.css"/>


    <script src="https://code.jquery.com/jquery-3.6.1.min.js"
            integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
            crossorigin="anonymous"></script>

</head>
<script>
    navigator.geolocation.getCurrentPosition(getSuccess, getError);
    function getSuccess(position){
        const lat = position.coords.latitude;
        const lng = position.coords.longitude;
        const accuracy = Math.floor(position.coords.accuracy);
        $("#create").on("click",function(){
            $('input[name=inputLAT]').attr('value',lat);
            $('input[name=inputLNT]').attr('value',lng);
        });

    }
    function getError() {
        alert('Geolocation Error');
    }

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
                        '<td scope="col">-</td>'+  //거리
                        '<td scope="col">' +'</td>'+   //관리번호
                        '<td scope="col">'+item.X_SWIFI_WRDOFC+'</td>'+   //자치구
                        '<td scope="col">'+item.X_SWIFI_MAIN_NM+'</td>'+  //와이파이명
                        '<td scope="col">'+item.X_SWIFI_ADRES1+'</td>'+   //도로명주소
                        '<td scope="col">'+item.X_SWIFI_ADRES2+'</td>'+   // 상세주소
                        '<td scope="col">'+item.X_SWIFI_INSTL_FLOOR+'</td>'+  //설치위치(층)
                        '<td scope="col">'+item.X_SWIFI_INSTL_TY+'</td>'+   // 설치유형
                        '<td scope="col">'+item.X_SWIFI_INSTL_MBY+'</td>'+   //설치기관
                        '<td scope="col">'+item.X_SWIFI_SVC_SE+'</td>'+ // 서비스구분
                        '<td scope="col">'+item.X_SWIFI_CMCWR+'</td>'+ // 망종류
                        '<td scope="col">'+item.X_SWIFI_CNSTC_YEAR+'</td>'+ // 설치년도
                        '<td scope="col">'+item.X_SWIFI_INOUT_DOOR+'</td>'+ //실내외 구분
                        '<td scope="col">-</td>'+ // wifi 접속 현황 +item.X_SWIFI_REMARS3+
                        '<td scope="col">'+item.LAT+'</td>'+ // x좌표
                        '<td scope="col">'+item.LNT+'</td>'+ // y좌표
                        '<td scope="col">'+item.WORK_DTTM+'</td>'+  //작업일자
                        '</tr>'
                    );
                });


            },
            error:function(err){
                alert('네트워크 문제 발생');
            }
        });


    });
    // $("div").html(navigator.geolocation.getCurrentPosition(getSuccess, getError));

    $(function (){
        $("#create").on("click",function(){
            $('input[name=inputValue]').attr('value',navigator.geolocation.getCurrentPosition(getSuccess));
        });
    });

</script>
<body>
<%NearWifiService NearWifiService = new NearWifiService();
    List<NearWifi> nearWifiList = NearWifiService.wifiSelect();
    NearWifi near = new NearWifi();
    String wifiName = near.getWifiName();
    near.setWifiName(wifiName);
    NearWifiService.wifiSelect(near);

    near.setAddress(near.getAddress());
    near.setRoadName(near.getRoadName());
    near.setInstallationType(near.getInstallationType());
    for (NearWifi wifi : nearWifiList){
%>
<tr>
    <td><%=wifi.getWifiName()%></td>
    <td><%=wifi.getWifiName()%></td>

</tr>
<%}%>

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
    <input name="inputLAT" value=""/>
    ,
    <span>LNT</span>
    <input name="inputLNT" value=""/>
    <button id="create">내위치 가져오기</button>
    <button>근처 WIFI 정보보기</button>
    <button id="load-btn">데이터로드</button>
</div>

<table class="table" style="margin-top:30px;" id="customers">
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
