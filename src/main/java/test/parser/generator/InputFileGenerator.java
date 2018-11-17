package test.parser.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 * генератор входных файлов (для тестирования парсера)
 * 
 * @author kami
 *
 */
public class InputFileGenerator {

	private List<String> currencies = Arrays.asList("RUB", "USD", "EUR", "CHF", "JPY");
	private List<String> comments   = Arrays.asList("покупка","оплата заказа","перевод","платёж");
	private Set<Integer> generatedId = new TreeSet<>();
	
	 
	Collection<File> getAllInputFilesOfType(String directoryName, String type) {
	    File directory = new File(directoryName);
	    return FileUtils.listFiles(directory, new WildcardFileFilter(type), null);
	}
	
	private String getFileName(String type) throws IOException {
		Collection<File> files = getAllInputFilesOfType(".", "input*."+type);
		int max = 0;
		for(File file: files) {
			String name = file.getName().toLowerCase();
			max = Math.max(max, Integer.parseInt(name.replace("input", "").replace("."+type.toLowerCase(), "")));
		}
		return String.format("input%03d.%s", max+1, type);
	}

	private OutputRecord makeRecord() {
		Random rnd = new Random();
		int newOrderId = 0;
		do {
			newOrderId = rnd.nextInt(10000000);
		} while(generatedId.contains(newOrderId));
		generatedId.add(newOrderId);
		return new OutputRecord(newOrderId, 
				               rnd.nextFloat()*1000, 
				               currencies.get(rnd.nextInt(currencies.size())), 
				               comments.get(rnd.nextInt(comments.size())));
	}
	
	private OutputRecord makeJunkRecord() {
		Random rnd = new Random();
		OutputRecord or = makeRecord();
		if (rnd.nextFloat() > 0.75)
			or.setAmount("JUNK");
		else if (rnd.nextFloat() > 0.50) {
			or.setOrderId("JUNK");
		} else if (rnd.nextFloat() > 0.25) {
			or.setCurrency("JUNK");
		} else if (rnd.nextFloat() > 0.125) {
			or.setOrderId("JUNK");
			or.setCurrency("JUNK");
		} else {
			or.setAmount("JUNK");
			or.setCurrency("JUNK");
		}
		return or;
	}
	
	private void generate(int numOfRecords, String type) throws IOException {
		try (FileWriter writer = new FileWriter(getFileName(type), true)) {
			for (int i = 0; i < numOfRecords; i++) {
				OutputRecord record = (new Random().nextFloat() > 0.1) 
						            ? makeRecord()
						            : makeJunkRecord();
				if (type.equalsIgnoreCase("csv"))
					writer.write(String.format("%s%n", record.toCSV()));
				else if (type.equalsIgnoreCase("json"))
					writer.write(String.format("%s%n", record.toJSON()));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		InputFileGenerator ifg = new InputFileGenerator();
		Arrays.asList(100000,100000,1000000,1000000)
		      .stream()
		      .forEach(n -> {
				try {
					ifg.generate(n, (new Random().nextFloat() > 0.5) ? "csv" : "json");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
	}
	
}
