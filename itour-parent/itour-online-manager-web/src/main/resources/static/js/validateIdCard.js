function checkIdcard(idcard){
    var Errors=new Array(
        '\u9A8C\u8BC1\u901A\u8FC7!',
        '\u8EAB\u4EFD\u8BC1\u53F7\u7801\u4F4D\u6570\u4E0D\u5BF9!',
        '\u8EAB\u4EFD\u8BC1\u53F7\u7801\u51FA\u751F\u65E5\u671F\u8D85\u51FA\u8303\u56F4\u6216\u542B\u6709\u975E\u6CD5\u5B57\u7B26!',
        '\u8EAB\u4EFD\u8BC1\u53F7\u7801\u6821\u9A8C\u9519\u8BEF!',
        '\u8EAB\u4EFD\u8BC1\u5730\u533A\u975E\u6CD5!'
    );
    var area={11:"\u5317\u4EAC",12:"\u5929\u6D25",13:"\u6CB3\u5317",14:"\u5C71\u897F",15:"\u5185\u8499\u53E4",21:"\u8FBD\u5B81",22:"\u5409\u6797",23:"\u9ED1\u9F99\u6C5F",31:"\u4E0A\u6D77",32:"\u6C5F\u82CF",33:"\u6D59\u6C5F",34:"\u5B89\u5FBD",35:"\u798F\u5EFA",36:"\u6C5F\u897F",37:"\u5C71\u4E1C",41:"\u6CB3\u5357",42:"\u6E56\u5317",43:"\u6E56\u5357",44:"\u5E7F\u4E1C",45:"\u5E7F\u897F",46:"\u6D77\u5357",50:"\u91CD\u5E86",51:"\u56DB\u5DDD",52:"\u8D35\u5DDE",53:"\u4E91\u5357",54:"\u897F\u85CF",61:"\u9655\u897F",62:"\u7518\u8083",63:"\u9752\u6D77",64:"\u5B81\u590F",65:"\u65B0\u7586",71:"\u53F0\u6E7E",81:"\u9999\u6E2F",82:"\u6FB3\u95E8",91:"\u56FD\u5916"}
    var idcard,Y,JYM;
    var S,M;
    var idcard_array = new Array();
    idcard_array = idcard.split("");
//地区检验
    if(area[parseInt(idcard.substr(0,2))]==null) return Errors[4];
//身份号码位数及格式检验
    switch(idcard.length){
        case 15:
            if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
                ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
            } else {
                ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
            }
            if(ereg.test(idcard)) return Errors[0];
            else return Errors[2];
            break;
        case 18:
//18位身份号码检测
//出生日期的合法性检查
//闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
//平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
            if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){
                ereg=/^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
            } else {
                ereg=/^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
            }
            if(ereg.test(idcard)){//测试出生日期的合法性
//计算校验位
                S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
                + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
                + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
                + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
                + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
                + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
                + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
                + parseInt(idcard_array[7]) * 1
                + parseInt(idcard_array[8]) * 6
                + parseInt(idcard_array[9]) * 3 ;
                Y = S % 11;
                M = "F";
                JYM = "10X98765432";
                M = JYM.substr(Y,1);//判断校验位
                if(M == idcard_array[17]) return Errors[0]; //检测ID的校验位
                else return Errors[3];
            }
            else return Errors[2];
            break;
        default:
            return Errors[1];
            break;
    }
}