import java.util.TreeMap;

public class InMemorySimpleDB {
    private TreeMap<Long, Record> accountIndex = new TreeMap<>();
    private TreeMap<String, Record> nameIndex = new TreeMap<>(); 
    private TreeMap<Double, Record> valueIndex = new TreeMap<>(); 

    public void addRecord(Record record) {
        accountIndex.put(record.getAccount(), record);
        nameIndex.put(record.getName(), record);
        valueIndex.put(record.getValue(), record);
    }

    public void deleteRecord(long account) {
        Record record = accountIndex.remove(account);
        if (record != null) {
            nameIndex.remove(record.getName());
            valueIndex.remove(record.getValue());
        }
    }

    public void updateRecord(Record record) {
        Record existingRecord = accountIndex.get(record.getAccount());
        if (existingRecord != null) {
            nameIndex.remove(existingRecord.getName());
            valueIndex.remove(existingRecord.getValue());
        }

        accountIndex.put(record.getAccount(), record);
        nameIndex.put(record.getName(), record);
        valueIndex.put(record.getValue(), record);
    }

    public Record getRecord(String key, String value) {
        switch (key) {
            case "account":
                long account = Long.parseLong(value);
                return accountIndex.get(account);
            case "name":
                return nameIndex.get(value); 
            case "value":
                double doubleValue = Double.parseDouble(value);
                return valueIndex.get(doubleValue);
            default:
                return null;
        }
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

        // Добавление записей
        Record record1 = new Record(234678L, "Иванов Иван Иванович", 2035.34);
        Record record2 = new Record(4112221L, "Алексеев Алексей Алексеевич", 2024.08);
        Record record3 = new Record(987654L, "Сидоров Сидор Сидорович", 1500.50);
        db.addRecord(record1);
        db.addRecord(record2);
        db.addRecord(record3);

        // Поиск по разным полям
        System.out.println("\nПоиск по полю name:");
        System.out.println(db.getRecord("name", "Иванов Иван Иванович"));

        System.out.println("\nПоиск по полю value:");
        System.out.println(db.getRecord("value", "2024.08"));

        System.out.println("\nПоиск по полю account:");
        System.out.println(db.getRecord("account", "987654"));

        // Изменение записи
        record3.setValue(3000.00);
        db.updateRecord(record3);
        System.out.println("\nЗапись после изменения:");
        System.out.println(db.getRecord("account", "987654"));

        // Удаление записи
        db.deleteRecord(4112221L);
        System.out.println("\nВсе записи после удаления:");
        for (Record record : db.accountIndex.values()) {
            System.out.println(record);
        }
    }
}
