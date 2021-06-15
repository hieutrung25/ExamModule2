package iService;

import model.DirectoryBook;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DirectoryBookManage implements iManage<DirectoryBook> {
    public static List<DirectoryBook> directoryBookList = new ArrayList<>();
    static{

        directoryBookList.add(new DirectoryBook("Tien",913970994L,"CodeGym",true,"HaiPhong",LocalDate.parse("1994-10-23"),"tiennv@gmail.com"));
        directoryBookList.add(new DirectoryBook("Hung",913970995L,"FTU",true,"HaNam",LocalDate.parse("1994-10-24"),"hungnd@gmail.com"));
        directoryBookList.add(new DirectoryBook("Son",913970996L,"FPT",true,"NamDinh",LocalDate.parse("1994-10-24"),"codegym@gmail.com"));
        directoryBookList.add(new DirectoryBook("Truong",913970997L,"TMA",true,"Can Tho",LocalDate.parse("1994-10-24"),"cantho@gmail.com"));
    }
    @Override
    public void add(DirectoryBook item) {
        if(!isExisted(item.getPhoneNumber()))
            directoryBookList.add(item);
    }

    @Override
    public void update(long phone, DirectoryBook item) {
        if(isExisted(phone)){
            for (int i = 0; i < directoryBookList.size(); i++) {
                if(directoryBookList.get(i).getPhoneNumber() == phone){
                    directoryBookList.set(i,item);
                }
            }
        }
    }

    @Override
    public List<DirectoryBook> delete(long phone) {
        if(isExisted(phone)){
            for (int i = 0; i < directoryBookList.size(); i++) {
                if(directoryBookList.get(i).getPhoneNumber()==phone){
                    directoryBookList.remove(directoryBookList.get(i));
                }
            }
        }
        return directoryBookList;
    }

    @Override
    public List<DirectoryBook> findAll() {
        return directoryBookList;
    }

    @Override
    public DirectoryBook findByPhone(long phone) {
        if(isExisted(phone)){
            for (int i = 0; i < directoryBookList.size(); i++) {
                if(directoryBookList.get(i).getPhoneNumber()==phone)
                    return directoryBookList.get(i);
            }
        }
        return null;
    }

    @Override
    public List<DirectoryBook> findByName(String name) {
        List<DirectoryBook> list = new ArrayList<>();
        for (int i = 0; i < directoryBookList.size(); i++) {
            if(directoryBookList.get(i).getName().equals(name)){
                list.add(directoryBookList.get(i));
            }
        }
        return list;
    }

    public boolean isExisted(long phone){
        for (DirectoryBook book: directoryBookList) {
            if(book.getPhoneNumber()==phone) {
                return true;
            }
        }
        return false;
    }

    public void readFile(){
        try {
            FileReader file = new FileReader("D:\\ExamModule2\\src\\data\\contacts.csv");
            BufferedReader bf = new BufferedReader(file);

            String line = null;
            String[] directoryBookString;
            while((line = bf.readLine())!=null){
                directoryBookString = line.split(",");
                System.out.println("Directory Book: name" + directoryBookString[0]+"phone"+directoryBookString[1]+"group"+directoryBookString[2]
                +"Sex: " + directoryBookString[3]+"Address"+directoryBookString[4]+"Birthday"+directoryBookString[5]+"mail"+directoryBookString[6]);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeFile(List list){
        try {
            FileWriter file = new FileWriter("D:\\ExamModule2\\src\\data\\writtenList.txt");
            for (int i = 0; i < directoryBookList.size(); i++) {
                  file.write(directoryBookList.get(i).toString());
            }
           file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

