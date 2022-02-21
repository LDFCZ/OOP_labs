#pragma once

#include "Creator.h"
#include "ReplaceWorker.h"

namespace MainWorkerCreators {

	class ReplaceCreator : public Interfaces::Creator {
	public:
		Interfaces::Worker* create_worker(std::vector<std::wstring>&, MainContext::Context&) const override;
	};
}
