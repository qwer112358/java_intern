import java.util.HashMap;
import java.util.Map;

public class InMemorySimpleDB {
    private Map<Long, Record> data = new HashMap<>();

    public void addRecord(Record record) {
        long account = record.getAccount();
        data.put(account,record);
    }

    public void deleteRecord(long account) {
        data.remove(account);
    }

    public void updateRecord(Record record) {
        long account = record.getAccount();
        if (data.containsKey(account)) {
            data.put(account,record);
        }
    }

    public Record getRecord(String key, String value) {
        if ("account".equals(key)) {
            long account = Long.parseLong(value);
            if (data.containsKey(account)) {
                return data.get(account);
            }
        } else if ("name".equals(key)) {
            for (Record record : data.values()) {
                if (record.getName().equals(value)) {
                    return record;
                }
            }
        } else if ("value".equals(key)) {
            for (Record record: data.values()) {
                if (record.getValue() == Double.parseDouble(value))
                    return record;
            }
        }
        return null;
    }

    public Map<Long, Record> getData() {
        return data;
    }

    static class Record {
        private Long account;
        private String name;
        private Double value;

        public Long getAccount() {
            return account;
        }

        public void setAccount(Long account) {
            this.account = account;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        public Record(Long account, String name, Double value) {
            this.account = account;
            this.name = name;
            this.value = value;
        }

       @Override
       public String toString() {
           return "Record{" +
                   "account=" + account +
                   ", name='" + name + '\'' +
                   ", value=" + value +
                   '}';
       }
   }
       
    public static void main(String[] args) {
        InMemorySimpleDB db = new InMemorySimpleDB();
        Record record1 = new Record(234678L, "Иванов Иван Иванович", 2035.34);
        Record record2 = new Record(4112221L, "Алексеев Алексей Алексеевич", 2024.08);
        db.addRecord(record1);
        db.addRecord(record2);
        System.out.println(db.getRecord("name", "Иванов Иван Иванович"));
        System.out.println(db.getRecord("value", "2024.08"));
        System.out.println(db.getData().values());
    }
}