import java.util.Optional;

public class FakeData {
    private int index = 0;
    private final String[] records = {"RéC1", "REC2", "REC3", "REC4", "REC5", "REC6", "REC7", "REC8", "REC9", "REC10"};


    public Optional<String> nexRecord() {
        return index == records.length ? Optional.empty() : Optional.of(records[index++] + "\n");
    }

}
