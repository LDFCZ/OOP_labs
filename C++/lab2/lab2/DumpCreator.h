#pragma once

#include "Creator.h"
#include "DumpWorker.h"

namespace MainWorkerCreators {

	class DumpCreator : public Interfaces::Creator {
	public:
		Interfaces::Worker* create_worker(std::vector<std::wstring>&, MainContext::Context&) const override;
	};
}
