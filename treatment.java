import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class treatment {
    //=============================================================================================
    public boolean isNumber(String str) {
        /*Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if ( !isNum.matches() ){
            return false;
        }
        return true;*/
        return str.matches("[0-9]{4}");

    }

    //======================================================================================================================
    public void showStockList() {
        Path path1 = Paths.get("D:\\java project\\Learning\\hau0916\\src\\stock.csv");
        try (BufferedReader reader = Files.newBufferedReader(path1, StandardCharsets.UTF_8)) {
            String[] data1;
            System.out.println("|===================================================|");
            int count = 1;
            for (String line; (line = reader.readLine()) != null; ) {
                data1 = line.split("\t");
                if (count == 1) {
                    for (int i = 0; i < data1.length; i++) {

                        if (data1[i].length() > 15) {
                            data1[i] = data1[i].substring(0, 14) + "...";
                        }
                        if (i == 0) {
                            System.out.printf("%-5s", "| " + data1[i] + " ");

                        } else if (i == 1) {
                            System.out.printf("%-20s", "| " + data1[i]);

                        } else if (i == 2) {
                            System.out.printf("%-11s", "| " + data1[i]);
                            System.out.print("|");

                        } else {
                            System.out.printf("%13s", data1[i]);
                            System.out.println("|");
                        }
                    }
                    System.out.println("|+++++++++++++++++++++++++++++++++++++++++++++++++++|");
                } else {
                    for (int i = 0; i < data1.length; i++) {
                        if (data1[i].length() > 15) {
                            data1[i] = data1[i].substring(0, 14) + "...";
                        }
                        if (i == 0) {
                            System.out.printf("%-5s", "| " + data1[i] + " ");

                        } else if (i == 1) {
                            System.out.printf("%-20s", "| " + data1[i]);

                        } else if (i == 2) {
                            System.out.printf("%-11s", "| " + data1[i]);
                            System.out.print("|");

                        } else {

                            if (data1[i].length() > 10) {
                                data1[i] = data1[i].substring(0, 8) + "...";
                            }
                            long num1 = Long.parseLong(data1[i]);
                            String num2 = String.format("%,d", num1);
                            System.out.printf("%13s", num2);
                            System.out.println("|");
                        }
                    }
                }
                count++;
            }
            System.out.println("=====================================================");
            //System.out.printf("%,d", 1000);
        } catch (IOException ex) {
            System.out.println("err");
        }
    }

    //===================================================================================================================
    public boolean isExist(String newCode) {

        Path path1 = Paths.get("D:\\java project\\Learning\\hau0916\\src\\stock.csv");
        try (BufferedReader reader = Files.newBufferedReader(path1, StandardCharsets.UTF_8)) {
            String oldCode;

            for (String line; (line = reader.readLine()) != null; ) {
                oldCode = line.split("\t")[0];
                if (newCode.equals(oldCode)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            System.out.println("err");
        }
        return false;
    }

    //===============================================================================================
    public void addingNewStock() {
        Scanner scr = new Scanner(System.in);
        stock addNewStock = new stock();
        treatment usingTreatment = new treatment();
        //usingTreatment.showStockList();
        System.out.println("please enter the name:");
        while (true) {
            String newProductName = scr.nextLine();
            if (newProductName.isBlank()) {
                System.out.println("plz enter");
                continue;
            } else if (!newProductName.matches("^[a-zA-Z].*")) {
                System.out.println("plz enter english");
                continue;
            } else {
                addNewStock.setProduct_name(newProductName);
                System.out.println(addNewStock.getProduct_name());
                break;
            }
        }


        System.out.println("please enter the code:");
        while (true) {
            String newCode = scr.nextLine();
            if (newCode.isBlank()) {
                System.out.println("plz enter");
                continue;
            } else if (newCode.matches("[1-9][0-9]{3}")) {
                if (isExist(newCode)) {
                    System.out.println("code already exists, plz try again");
                    return;
                } else {
                    addNewStock.setCode(newCode);
                    break;
                }
            } else {
                System.out.println("please enter the code with 4 numbers:");
                continue;
            }

        }

        System.out.println("please enter the market type:");
        while (true) {

            //market = market.toUpperCase();
            //String market1 = market.toUpperCase();
            String market = scr.nextLine();
            if (!market.isBlank()) {

                if (market.equals("1")) {
                    addNewStock.setMarket("PRIME");
                    break;
                } else if (market.equals("2")) {
                    addNewStock.setMarket("STANDARD");
                    break;
                } else if (market.equals("3")) {
                    addNewStock.setMarket("GROWTH");
                    break;
                } else if (market.equals("PRIME") || market.equals("STANDARD") || market.equals("GROWTH")) {
                    addNewStock.setMarket(market);
                    break;
                } else {
                    System.out.println("please choose 1P or 2S or 3G");
                    continue;
                }


            } else {
                System.out.println("plz enter");
                continue;
            }
        }
        System.out.println("please enter the Shares Issued: ");
        while (true) {

            String recordShareIssued = scr.nextLine();
            if (recordShareIssued.matches("[1-9][0-9]*")) {
                try {
                    addNewStock.setShares_issued(Long.parseLong(recordShareIssued));
                    break;
                } catch (NumberFormatException numErr) {
                    System.out.println("plz enter the right number");
                }
            } else if (recordShareIssued.isBlank()) {
                System.out.println("plz enter");
                continue;
            } else {
                System.out.println("please enter with numbers(don't start by 0 or space):");
                continue;
            }
        }


        Path path = Paths.get("D:\\java project\\Learning\\hau0916\\src\\stock.csv");


        try {
            File file = new File("D:\\java project\\Learning\\hau0916\\src\\stock.csv");
            try (FileWriter fr = new FileWriter(file, true)) {
                fr.write("\n");
                fr.write(addNewStock.getCode() + "\t");
                fr.write(addNewStock.getProduct_name() + "\t");
                fr.write(addNewStock.getMarket() + "\t");

                long getIssuedShares = addNewStock.getShares_issued();
                fr.write(Long.toString(getIssuedShares));
            }

        } catch (IOException ex) {
            System.out.println("loading err");
        }
        System.out.println("finished adding.");
    }

    //==========================================================================================
    public void addNewTrade() {
        Scanner scr = new Scanner(System.in);
        Trade addTrade = new Trade();
        treatment treatment = new treatment();
        System.out.print("""
                取引日時を12桁の半角数字で入力してください。
                例:2023年9月22日10時30分 → 202309221030
                """);
        while (true) {
            String inputDateTime = scr.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            try {
                LocalDateTime userDateTime = LocalDateTime.parse(inputDateTime, formatter);
                if (treatment.isPassedTime(userDateTime)) {
                    if (treatment.isWorkDay(userDateTime)) {
                        if (treatment.isInTimeZone(userDateTime)) {
                            addTrade.setTradeDateTime(userDateTime);
                            break;
                        } else {
                            System.out.println("時間帯:9:00~15:00の取引日時を入力してください。");
                            continue;
                        }
                    } else {
                        System.out.println("平日である取引日時を入力してください。");
                        continue;
                    }
                } else {
                    System.out.println("過去の取引日時を入力してください。");
                    continue;
                }
            } catch (Exception exception) {
                System.out.print("""
                        無効日時です。
                        正しい取引日時を12桁の半角数字で入力してください。
                        例:2023年9月22日10時30分 → 202309221030
                        """);
            }

        }
        System.out.println("銘柄コードを入力してください。");
        while (true) {
            String inputCode = scr.nextLine();
            if (!inputCode.isBlank()) {
                if (inputCode.matches("[1-9][0-9]{3}")) {
                    if (isExist(inputCode)) {
                        addTrade.setCode(inputCode);
                        break;
                    } else {
                        System.out.print("""
                                この銘柄は存在していません。
                                他の銘柄コードを入力してください。
                                """);
                        continue;
                    }
                } else {
                    System.out.println("4桁の半角数字で入力してください。");
                    continue;
                }
            } else {
                System.out.println("入力してください。");
                continue;
            }
        }
        System.out.print("""
                売買区分を選択してください。
                1.売
                2.買
                番号を選択して、1桁の半角数字で番号を入力してください。
                """);
        while (true) {
            String inputTradeType = scr.nextLine();
            if (!inputTradeType.isBlank()) {
                if (inputTradeType.matches("[1-2]")) {
                    if (inputTradeType.equals("1")) {
                        addTrade.setType(TradeType.sell);
                        break;
                    } else {
                        addTrade.setType(TradeType.buy);
                        break;
                    }
                } else {
                    System.out.print("""
                            1.売
                            2.買
                              番号を選択して、1桁の半角数字で番号を入力してください。
                              """);
                    continue;
                }
            } else {
                System.out.println("入力してください。");
                continue;
            }
        }
        System.out.println("株取引数量を半角数字で入力してください。(100単位)");
        while (true) {
            String inputQuantity = scr.nextLine();
            if (!inputQuantity.isBlank()) {
                if (inputQuantity.matches("[0-9]*")) {
                    if (inputQuantity.matches("[1-9][0-9]*")) {
                        try{if (Long.parseLong(inputQuantity) % 100 == 0) {
                            addTrade.setQuantity(Long.parseLong(inputQuantity));
                            break;
                        } else {
                            System.out.println("最小単位は100で取引数量を入力してください。");
                            continue;
                        }
                        }catch(NumberFormatException numberFormatException){
                            System.out.println("範囲が正しい取引数量を入力してください。");
                            continue;
                        }
                    } else {
                        System.out.println("最初の数字に0を入れないでください。");
                        continue;
                    }
                } else {
                    System.out.println("半角数字だけで入力してください。");
                    continue;
                }
            } else {
                System.out.println("入力してください。");
                continue;
            }
        }
        System.out.println("価格を半角数字で入力してください。");
        while(true){
               String price = scr.nextLine();
               if(!price.isBlank()){
                   if(price.matches("^[0-9\\.]+$")){
                     try{  BigDecimal bigDecimalPrice = new BigDecimal(price);
                       addTrade.setPrice(bigDecimalPrice);
                       System.out.println(addTrade.getPrice());
                       break;
                     }
                     catch (NumberFormatException numberFormatException){
                         System.out.println("正しい価格を半角数字で入力してください。");
                         continue;
                     }
                  }else{
                       System.out.println("正しい価格を半角数字で入力してください。");
                       continue;
                   }
               }else{
                   System.out.println("入力してください。");
                   continue;
               }




        }
    }
//=================================================================================
public  String codeToName(String code){
        String name;stock set =new stock();
        Path path1 = Paths.get("D:\\java project\\Learning\\hau0916\\src\\stock.csv");
        try (BufferedReader reader = Files.newBufferedReader(path1, StandardCharsets.UTF_8)) {
            String[] charLine;
            for (String line; (line = reader.readLine()) != null; ) {
                charLine = line.split("\t");
                if (code.equals(charLine[0])) {
                    name=charLine[1];
                    set.setProduct_name(name);
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("err");
        }return set.getProduct_name();
    }

    //=================================================================================
    public boolean isWorkDay(LocalDateTime userDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DayOfWeek dayOfWeek = userDateTime.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }

    //===============================================================================
    public boolean isInTimeZone(LocalDateTime userDateTime) {
        LocalTime startTime = LocalTime.of(8, 59);
        LocalTime endTime = LocalTime.of(15, 1);
        LocalTime userTime = userDateTime.toLocalTime();
        return userTime.isAfter(startTime) && userTime.isBefore(endTime);
    }

    //===========================================================================================
    public boolean isPassedTime(LocalDateTime useDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return useDateTime.isBefore(currentDateTime);
    }
//===============================================================================================

}

