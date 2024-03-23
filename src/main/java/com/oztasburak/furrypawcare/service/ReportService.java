package com.oztasburak.furrypawcare.service;

import com.oztasburak.furrypawcare.config.BaseService;
import com.oztasburak.furrypawcare.config.ModelMapperService;
import com.oztasburak.furrypawcare.dto.request.ReportRequest;
import com.oztasburak.furrypawcare.dto.response.ReportResponse;
import com.oztasburak.furrypawcare.dto.response.ReportResponse;
import com.oztasburak.furrypawcare.entity.Report;
import com.oztasburak.furrypawcare.entity.Report;
import com.oztasburak.furrypawcare.repository.ReportRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService implements BaseService<Report, ReportRequest, ReportResponse> {
    private final ReportRepository reportRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public Report getById (Long id)
        {
            return reportRepository.findById(id)
                    .orElseThrow (() -> new EntityNotFoundException ("Report with ID" + id + " not found"));
        }

    @Override
    public ReportResponse getResponseById (Long id)
        {
            return modelMapperService
                    .forResponse ()
                    .map (getById (id), ReportResponse.class);
        }

    @Override
    public List<Report> getAll ()
        {
            return reportRepository.findAll ();
        }

    @Override
    public List<ReportResponse> getAllResponses ()
        {
            return getAll ()
                    .stream ().map (report -> modelMapperService
                            .forResponse ()
                            .map (report, ReportResponse.class))
                    .toList ();
        }

    @Override
    public ReportResponse create (ReportRequest reportRequest)
        {
            Report report = modelMapperService
                    .forRequest ()
                    .map (reportRequest, Report.class);

            return modelMapperService
                    .forResponse ()
                    .map (reportRepository.save (report), ReportResponse.class);
        }

    @Override
    public ReportResponse update (Long id , ReportRequest reportRequest)
        {
            Report doesReportExist = getById (id);
            Report report = modelMapperService
                    .forRequest ()
                    .map (reportRequest, Report.class);

            modelMapperService
                    .forRequest ()
                    .map (report, doesReportExist);
            doesReportExist.setId (id);

            return modelMapperService
                    .forResponse ()
                    .map (reportRepository.save (doesReportExist), ReportResponse.class);
        }

    @Override
    public void deleteById (Long id)
        {
            reportRepository.delete (getById (id));
        }
}
