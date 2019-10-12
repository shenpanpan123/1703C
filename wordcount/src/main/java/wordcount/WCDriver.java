package wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WCDriver {

	public static void main(String[] args) throws Exception {
		//dsfnhiuhsndf
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "词频统计");
		job.setJarByClass(WCDriver.class);
		job.setMapperClass(WCMapper.class);
		job.setReducerClass(WCReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		
		job.setNumReduceTasks(4);
		job.setCombinerClass(WCReducer.class);
		FileInputFormat.setInputPaths(job, new Path("hdfs://node11:9000/user/spp/data/1704k.txt"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://node11:9000/user/spp/wcResult3"));

		if (!job.waitForCompletion(true))
			return;
	}

}
