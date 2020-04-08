function formatNum(str, num) {
    if ((str+"").indexOf(",") >= 0) {
        return str
    } else {
        var numAF = 3;
        var buStrDian = ".00";
        var buStr = "00";
        if (num !== undefined) {
            numAF = num;
            buStrDian = ".00000000";
            buStr = "00000000"
        }
        str = str ? str : 0;
        if (str != undefined) {
            str = str.toString();
            var newStr = "";
            var count = 0;
            var mius = "";
            if (str.indexOf("-") == -1) {
            } else {
                mius = "-";
                str = str.substr(1)
            }
            if (str.indexOf(".") == -1) {
                for (var i = str.length - 1; i >= 0; i--) {
                    if (count % 3 == 0 && count != 0) {
                        newStr = str.charAt(i) + "," + newStr
                    } else {
                        newStr = str.charAt(i) + newStr
                    }
                    count++
                }
                str = newStr + buStrDian;
                return mius + str
            } else {
                for (var i = str.indexOf(".") - 1; i >= 0; i--) {
                    if (count % 3 == 0 && count != 0) {
                        newStr = str.charAt(i) + "," + newStr
                    } else {
                        newStr = str.charAt(i) + newStr
                    }
                    count++
                }
                str = newStr + (str + buStr).substr((str + buStr).indexOf("."), numAF);
                return mius + str
            }
        }
    }
}
function formatWeight(str) {
    if ((str+"").toString().indexOf(",") >= 0) {
        return str;
    } else {
        str = str ? str : 0;
        if (str != undefined) {
            str = str.toString();
            var newStr = "";
            var count = 0;
            if (str.indexOf(".") == -1) {
                for (var i = str.length - 1; i >= 0; i--) {
                    if (count % 3 == 0 && count != 0) {
                        newStr = str.charAt(i) + "," + newStr
                    } else {
                        newStr = str.charAt(i) + newStr
                    }
                    count++
                }
                return str
            } else {
                for (var i = str.indexOf(".") - 1; i >= 0; i--) {
                    if (count % 3 == 0 && count != 0) {
                        newStr = str.charAt(i) + "," + newStr
                    } else {
                        newStr = str.charAt(i) + newStr
                    }
                    count++
                }
                return str
            }
        }
    }
};