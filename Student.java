import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class Student {
    private int id;
    private String name;
    private Map<String,Integer> attendanceMap;
    private int previousSemAttendance;
    private double aggregateAttendance;

    public Student(int id, String name, Map<String,Integer> attendanceMap, int previousSemAttendance){
        this.id = id;
        this.name = name;
        this.attendanceMap = new HashMap<>(attendanceMap);
        this.previousSemAttendance = previousSemAttendance;
        this.aggregateAttendance = calculateAggregateAttendance();
        applyBonusIfEligible();
    }


    public int getId(){
        return id;
    }
     public String getName(){
        return name;
     }
     public Map<String,Integer> getAttendance(){    return attendanceMap;}
    public int getPreviousSemAttendance(){  return previousSemAttendance;}
    public double getAggregateAttendance(){ return aggregateAttendance;}

     public void updateAttendance(String subject, int attendancepercentage){
        attendanceMap.put(subject,attendancepercentage);
        this.aggregateAttendance = calculateAggregateAttendance();
        applyBonusIfEligible();
     }

     private double calculateAggregateAttendance(){
        if(attendanceMap.isEmpty()) return 0;
        int total=0;
        for(int value : attendanceMap.values()){
            total += value;
        }
        return total/attendanceMap.size();
     }

     private void applyBonusIfEligible(){
        if(previousSemAttendance>=85 && aggregateAttendance<75){
            int bonus = calculateBonus(previousSemAttendance);
            aggregateAttendance = Math.min(aggregateAttendance+bonus, 100);
        }
     }

     private int calculateBonus(int previousSemAttendance){
        if(previousSemAttendance>=95)  return 10;
        else if(previousSemAttendance>=90)  return 7;
        else if(previousSemAttendance>=87)  return 5;
        else return 3;
     }

     public String toString(){
        StringBuilder sb = new StringBuilder(id+" "+name+" "+previousSemAttendance+" "+String.format("%.2f",aggregateAttendance));
        for(Map.Entry<String,Integer> entry : attendanceMap.entrySet()){
            sb.append(" ").append(entry.getKey()).append(": ").append(entry.getValue());
        }
        return sb.toString();

     }

}
