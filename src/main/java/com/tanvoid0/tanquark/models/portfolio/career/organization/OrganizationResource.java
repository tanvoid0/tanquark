package com.tanvoid0.tanquark.models.portfolio.career.organization;

import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo.AcademicVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo.NewAcademicVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo.AchievementVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo.NewAchievementVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo.CertificateVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo.NewCertificateVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.vo.ExperienceVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.vo.NewExperienceVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo.NewVoluntaryVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo.VoluntaryVO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.List;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
@RolesAllowed({"USER", "PORTFOLIO_USER"})
public class OrganizationResource {

    private final OrganizationService organizationService;

    // ========= Academic ================

    @GET
    @Path("/academic")
    public Response getAcademics() {
        final List<AcademicVO> academics = organizationService.getAcademics();
        return Response.ok(academics).build();
    }

    @POST
    @Path("/academic")
    public Response createAcademics(@RequestBody final NewAcademicVO request) {
        final AcademicVO response = organizationService.createAcademic(request);
        return Response.ok(response).status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/academic/batch")
    public Response createAcademics(@RequestBody final List<NewAcademicVO> request) {
        final List<AcademicVO> response = organizationService.createAcademicBatch(request);
        return Response.ok(response).status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/achievement")
    public Response getAchievements() {
        final List<AchievementVO> achievements = organizationService.getAchievements();
        return Response.ok(achievements).build();
    }

    @POST
    @Path("/achievement")
    public Response createAchievement(@RequestBody final NewAchievementVO request) {
        final AchievementVO response = organizationService.createAchievement(request);
        return Response.ok(response).status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/achievement/batch")
    public Response createAchievementBatch(@RequestBody final List<NewAchievementVO> request) {
        final List<AchievementVO> response = organizationService.createAchievementBatch(request);
        return Response.ok(response).status(Response.Status.CREATED).build();
    }


    @GET
    @Path("/certificate")
    public Response getCertificates() {
        final List<CertificateVO> certificates = organizationService.getCertificates();
        return Response.ok(certificates).build();
    }

    @POST
    @Path("/certificate")
    public Response createCertificate(@RequestBody final NewCertificateVO request) {
        final CertificateVO response = organizationService.createCertificate(request);
        return Response.ok(response).status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/certificate/batch")
    public Response createCertificateBatch(@RequestBody final List<NewCertificateVO> request) {
        final List<CertificateVO> response = organizationService.createCertificateBatch(request);
        return Response.ok(response).status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/experience")
    public Response getExperiences() {
        final List<ExperienceVO> experiences = organizationService.getExperiences();
        return Response.ok(experiences).build();
    }

    @POST
    @Path("/experience")
    public Response createExperience(@RequestBody final NewExperienceVO request) {
        final ExperienceVO response = organizationService.createExperience(request);
        return Response.ok(response).status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/experience/batch")
    public Response createExperienceBatch(@RequestBody final List<NewExperienceVO> request) {
        final List<ExperienceVO> response = organizationService.createExperienceBatch(request);
        return Response.ok(response).status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/voluntary")
    public Response getVoluntaries() {
        final List<VoluntaryVO> voluntaries = organizationService.getVoluntaries();
        return Response.ok(voluntaries).build();
    }

    @POST
    @Path("/voluntary")
    public Response createVoluntary(@RequestBody final NewVoluntaryVO request) {
        final VoluntaryVO response = organizationService.createVoluntary(request);
        return Response.ok(response).status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/voluntary/batch")
    public Response createVoluntaryBatch(@RequestBody final List<NewVoluntaryVO> request) {
        final List<VoluntaryVO> response = organizationService.createVoluntaryBatch(request);
        return Response.ok(response).status(Response.Status.CREATED).build();
    }


}
