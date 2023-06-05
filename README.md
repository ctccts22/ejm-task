<h2>프로젝트명: 부동산 공통코드 API</h2>

<h3>개요</h3>
<p>이 프로젝트는 부동산 업계에 적합한 유연한 공통 코드 시스템을 구축하는 것을 목표로 합니다. 공통 코드는 이름과 코드 값으로 구성되며, 각 공통 코드는 그룹이 있어 그룹 별로 조회하거나 개별적으로 조회할 수 있습니다.</p>

<h3>그룹 (Groups):</h3>
<ul>
<li>"부동산 유형" (PropertyType)</li>
<li>"거래 유형" (TransactionType)</li>
<li>"도시" (City)</li>
<li>"편의 시설" (Amenities)</li>
</ul>

<h3>코드 (Codes):</h3>
<ul>
<li>"부동산 유형" (PropertyType)에 대한 코드는 "단독 주택" (House), "아파트" (Apartment), "상업 건물" (Commercial Building) 등이 될 수 있습니다.</li>
<li>"거래 유형" (TransactionType)에 대한 코드는 "판매" (Sale), "임대" (Rent), "리스" (Lease) 등이 될 수 있습니다.</li>
<li>"도시" (City)는 부동산이 있는 여러 도시의 이름, 예를 들어 "서울" (Seoul), "부산" (Busan), "인천" (Incheon) 등이 될 수 있습니다.</li>
<li>"편의 시설" (Amenities)에 대한 코드는 "수영장" (Pool), "정원" (Garden), "주차장" (Parking) 등 여러 특징이 될 수 있습니다.</li>
</ul>

<h2>데이터베이스 설정</h2>
<pre>
create database ejmdb default character set utf8;
create user 'ejmid'@'%' identified by 'ejmpw';
grant all privileges on ejmdb.* to 'ejmid'@'%';
flush privileges;
</pre>

<h2>테이블 스키마</h2>
<h3>그룹 (Group) 테이블</h3>
<pre>
CREATE TABLE `group` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL
);
</pre>

<h3>코드 (Code) 테이블</h3>
<pre>
CREATE TABLE `code` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `value` VARCHAR(255) NOT NULL,
  `group_id` BIGINT NOT NULL,
  FOREIGN KEY (`group_id`) REFERENCES `group`(`id`)
);
</pre>

<h2>API 명세</h2>

<h3>그룹 (Group) APIs</h3>

<b>POST /api/groups</b> 
<p>새 그룹을 생성합니다. JSON 형식으로 그룹 이름을 요청 본문에 포함해야 합니다.</p>
<pre>
{
  "name": "부동산 유형"
}
</pre>

<b>GET /api/groups/{id}</b>
<p>특정 그룹의 세부 정보를 가져옵니다. 그룹 ID를 URL 경로 변수로 제공해야 합니다.</p>

<b>PUT /api/groups</b>
<p>기존 그룹의 이름을 업데이트합니다. JSON 형식으로 그룹 ID와 새로운 그룹 이름을 요청 본문에 포함해야 합니다.</p>
<pre>
{
  "id": 1,
  "name": "거래 유형"
}
</pre>

<b>DELETE /api/groups/{id}</b>
<p>특정 그룹을 삭제합니다. 그룹 ID를 URL 경로 변수로 제공해야 합니다.</p>

<h3>코드 (Code) APIs</h3>

<b>POST /api/codes</b>
<p>새 코드를 생성합니다. JSON 형식으로 코드 이름, 값, 그리고 그룹 ID를 요청 본문에 포함해야 합니다.</p>
<pre>
{
  "name": "아파트",
  "value": "AP",
  "groupId": 1
}
</pre>

<b>GET /api/codes/{id}</b>
<p>특정 코드의 세부 정보를 가져옵니다. 코드 ID를 URL 경로 변수로 제공해야 합니다.</p>

<b>PUT /api/codes</b>
<p>기존 코드를 업데이트합니다. JSON 형식으로 코드 ID, 새로운 코드 이름, 값, 그리고 그룹 ID를 요청 본문에 포함해야 합니다.</p>
<pre>
{
  "id": 1,
  "name": "상업 건물",
  "value": "CB",
  "groupId": 1
}
</pre>

<b>DELETE /api/codes/{id}</b>
<p>특정 코드를 삭제합니다. 코드 ID를 URL 경로 변수로 제공해야 합니다.</p>

<b>GET /api/codes/group/{groupId}</b>
<p>특정 그룹에 속한 모든 코드를 가져옵니다. 그룹 ID를 URL 경로 변수로 제공해야 합니다.</p>


