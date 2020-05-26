package com.example.demo.service;

import com.example.demo.entities.Cours;
import com.example.demo.entities.CoursFiles;
import com.example.demo.entities.User;
import com.example.demo.repository.CoursFilesRepository;
import com.example.demo.repository.CoursRepository;
import com.example.demo.repository.RemarqueRepository;
import com.example.demo.repository.UserRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.File;
import java.util.*;

@Service
@Transactional
public class CoursServiceImp implements CoursService {

    @Autowired
    private CoursRepository coursRepository;
    @Autowired
    private UploadPathService uploadPathService;
    @Autowired
    private CoursFilesRepository coursFilesRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ServletContext context;

    @Autowired
    private RemarqueRepository remarqueRepository;

    @Override
    public List<Cours> getCoursbyUser(HttpServletRequest httpServletRequest) {
        User user=userService.getUserConnect(httpServletRequest);
        List<Cours> cours = coursRepository.getCoursByUser(user.getId());

        return cours;

    }
    @Override
    public List<Cours> getAllCours() {

        return coursRepository.findAllByCreatedDate();
    }

    @Override
    public Cours saveCours(Cours cours, HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String email = securityContext.getAuthentication().getName();
        Set<User> users = userService.getUsers();
        User u = new User();
        for (User user : users) {
            if(user.getEmail().equals(email)){
                u=user;
            }
        }
        cours.setCreatedDate(new Date());

        cours.setUser(u);
        Cours newCours = coursRepository.save(cours);
        if (newCours != null && cours.getFiles() != null && cours.getFiles().size() > 0) {
            for (MultipartFile file : cours.getFiles()) {
                if(file!=null && StringUtils.hasText(file.getOriginalFilename())) {
                    String fileName = file.getOriginalFilename();
                    String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
                    File storeFile = uploadPathService.getFilePath(modifiedFileName, "files");
                    if (storeFile != null) {
                        try {
                            FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    CoursFiles files = new CoursFiles();
                    files.setFileExtension(FilenameUtils.getExtension(fileName));
                    files.setNomFile(fileName);
                    files.setModifiedFileName(modifiedFileName);
                    files.setCours(newCours);
                    coursFilesRepository.save(files);
                }
            }
        }
        return newCours;
    }



    @Override
    public Cours findById(Long id) {
        Optional<Cours> cours = coursRepository.findById(id);
        if(cours.isPresent()){
            return cours.get();
        }
        return null;

    }

    @Override
    public List<CoursFiles> findFilesbyCoursId(Long coursId) {
        return coursFilesRepository.findFilesbyCoursId(coursId);
    }

    @Override
    public Cours updateCours(Cours cours, HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String email = securityContext.getAuthentication().getName();
        Set<User> users = userService.getUsers();
        User u = new User();
        for (User user : users) {
            if(user.getEmail().equals(email)){
                u=user;
            }
        }

        cours.setUser(u);
        cours.setCreatedDate(new Date());
        Cours newCours = coursRepository.save(cours);

        if (cours!=null&&cours.getRemoveImages()!=null&&cours.getRemoveImages().size()>0){
            coursFilesRepository.deleteFileByCoursIdAndImageNames(cours.getId(),cours.getRemoveImages());
            for(String file : cours.getRemoveImages()){
                File dbFile = new File(context.getRealPath("/files/"+File.separator+file));
                if(dbFile.exists()){
                    dbFile.delete();
                }
            }
        }


        if (newCours != null && cours.getFiles() != null && cours.getFiles().size() > 0) {
            for (MultipartFile file : cours.getFiles()) {
                if(file!=null && StringUtils.hasText(file.getOriginalFilename())){
                    String fileName = file.getOriginalFilename();
                    String modifiedFileName = FilenameUtils.getBaseName(fileName)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(fileName);
                    File storeFile = uploadPathService.getFilePath(modifiedFileName, "files");
                    if (storeFile != null) {
                        try {
                            FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    CoursFiles files = new CoursFiles();
                    files.setFileExtension(FilenameUtils.getExtension(fileName));
                    files.setNomFile(fileName);
                    files.setModifiedFileName(modifiedFileName);
                    files.setCours(newCours);
                    coursFilesRepository.save(files);
                }


            }
        }
        return newCours;
    }


    @Override
    public void deleteFilesByCoursId(Long coursId) {
        List<CoursFiles> coursFiles = coursFilesRepository.findFilesbyCoursId(coursId);
        if(coursFiles!=null && coursFiles.size()>0){
            for(CoursFiles dbFile: coursFiles){
                File dbStroreFile= new File(context.getRealPath("/files/"+File.separator+dbFile.getModifiedFileName()));
                if(dbStroreFile.exists()){
                    dbStroreFile.delete();
                }
                coursFilesRepository.deleteFilesByCoursId(coursId);
            }
        }
    }

    @Override
    public void deleteCours(Long coursId) {

        coursRepository.deleteById(coursId);
    }

    @Override
    public void deleteRemarquesByCours(Long coursId) {
        remarqueRepository.deleteByCoursId(coursId);
    }
}