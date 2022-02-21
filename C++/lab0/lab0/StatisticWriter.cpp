#include "StatisticWriter.h"
#include "ConstSpace.h"

namespace MainClasses {

	StatisticWriter::StatisticWriter(Container::Context& word_context, FileInterface::CSVFileWriter* ofile) :word_context(word_context), ofile(ofile)
	{}

	void StatisticWriter::write_statistic() {
		std::vector<std::wstring> keys = this->word_context.get_sorted_keys();
		int words_count = this->word_context.get_count();

		*this->ofile << ConstSpace::FIRST_COLUMN_NAME << ConstSpace::SECOND_COLUMN_NAME << ConstSpace::THIRD_COLUMN_NAME << FileInterface::endrow;
		for (std::wstring key : keys)
		{
			int word_count = this->word_context.get_value_by_key(key);
			*this->ofile << key << word_count << ((float)word_count / (float)words_count * ConstSpace::PROPORTION) << FileInterface::endrow;
		}
	}
}