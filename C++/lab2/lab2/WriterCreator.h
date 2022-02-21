#pragma once

#include "Creator.h"
#include "WriterWorker.h"

namespace MainWorkerCreators {
	
	class WriterCreator : public Interfaces::Creator {
	public:
		Interfaces::Worker* create_worker(std::vector<std::wstring>&, MainContext::Context&) const override;
	};
}
