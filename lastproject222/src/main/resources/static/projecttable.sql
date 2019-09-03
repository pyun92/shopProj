select * from tab;
select * from user_sequences;

drop sequence user_sequences; 

select *from userdata
select *from BUCKET
select * from store

select * from PRODUCT_IMG
select * from product
drop table logindata
select * from options

insert into userdata(userseq, birthday ,manage_level, password, userid, username)
values(userdata_seq.nextval ,19910101, 2 ,'2' , '2' , 'ddd');

insert into report(reportseq,reportdate,reportdetail,reportdivi,reportname,reportsubject)
values(report_seq.nextval,'2019/09/03','상품질최악',1,'감자탕만드는남자','상품신고');
select * from report
delete from PRODUCT
delete from userdata
update report set confirmdate='2019/09/16' where reportseq=2;
update report set confirmdate='' where reportname='jym971024';
update report set productseq=1 where reportseq=3;