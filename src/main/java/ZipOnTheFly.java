import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

final public class ZipOnTheFly {
    private final OutputStream outputStream;
    private final FakeData data;

    public ZipOnTheFly(OutputStream fos, FakeData fakeData) {
        this.outputStream = fos;
        this.data = fakeData;
    }

    static public void main(String[] args) throws IOException {
        System.out.println("ZipOnTheFly Demo");

        FileOutputStream fos = new FileOutputStream("/Users/batman/IdeaProjects/ziponthefly/src/main/resources/onthefly_result.zip");

        ZipOnTheFly zip = new ZipOnTheFly(fos, new FakeData());

        zip.compress();

    }

    private void compress() throws IOException {

        ZipOutputStream archive = new ZipOutputStream(outputStream);

        ZipEntry entry = new ZipEntry("records.csv");
        archive.putNextEntry(entry);

        Optional<String> record = data.nexRecord();
        while (record.isPresent()) {
            archive.write(record.get().getBytes("UTF8"));
            record = data.nexRecord();
        }

        archive.closeEntry();
        archive.close();

    }
}
