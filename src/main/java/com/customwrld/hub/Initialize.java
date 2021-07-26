package com.customwrld.hub;

import com.customwrld.customlib.CustomLib;
import com.customwrld.customlib.hologram.Hologram;
import com.customwrld.customlib.hologram.component.HologramTextComponent;
import com.customwrld.customlib.npc.NPC;
import com.customwrld.hub.listeners.CancelledListeners;
import com.customwrld.hub.listeners.HubListeners;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Initialize {

    public static void init(Hub hub) {
//        try {
//            /* https://mineskin.org/383567938 */
//            NPC survivalNPC = new NPC("Survival", new Location(Bukkit.getWorld("world"), -12.5, 100, -16.5, 0, 0),
//                    "ewogICJ0aW1lc3RhbXAiIDogMTU4OTUwODg3NzI5MiwKICAicHJvZmlsZUlkIiA6ICIyYzEwNjRmY2Q5MTc0MjgyODRlM2JmN2ZhYTdlM2UxYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJOYWVtZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kYzVkNWU4OWQyYzk2ZTdkNTBkZWM3NzBiNjYwODZjZTM3MjQ3ZTA1M2FmYjliOGYwMjUyZmY2MzZkOWIyNzBhIgogICAgfQogIH0KfQ==",
//                    "ohiNvj5qOZ8F+wdHi//di3bHaIc0uA8QVZhxuynaJoyRKRJ7LgwylRuMAI4X8QmQuJgeA2EtXJuOcmdDVLHlDBC11dkCop6gPOpnNvF8a+yYPbC/wZqRBPgqZwuGyHFQ0o5cu/4WkuNmY/g/HycScSJGAAIL9M6l4fMDM6Xd/SsC+IYqxSPVLv3Ed+witjxGChxV5CRCs6szx66H6gbnnhSzhFz9dVtrYiAXRCWAv6F/OHhjbP5Ji8PRIOCydvw45mpMgw6IMyLHedkhIQ2Z8VsbnQc7WEh/Ki2V+Z8rpTaQBzDdLAVi5LNSz53iASKEDFNgiMBuD4kMhYjgT1ctOwiw/4GiBBpoCQumN12NOaVX9IMl518W7lq909D6Kh5KwYl+bv8FLkJPtqT63GqHMFzm0z6L3YUFtpkqyXKdWxq+5w2nm321GFS9gh/jTCe1AAm1Esl0ApzseYCTnWqmxnOxiva0ObP1zio1vJFVbvQuE311lRnd5QdyPkefowTLXBtY/PEzYDLsjLgLpq/dcvGkfNxgNTtZNj00BTOIPcJB8Et756EbJlsez7qfR5i3+hePpAYbDn8ceJ6/U4tX6ifG8M1auvM3ejtEhb8stgDNWPI1Alf7W2TCw4RwUHIEyjATUon1v+wfnPjQRAqXshIp18MlcUm5BPhpVwghGX8=",
//                    "&6&lVANILLA SURVIVAL", "&eThere are 0 players online", "&7&o(( Right Click to join this server ))");
//
//            /* https://mineskin.org/859887255 */
//            NPC hardCoreNPC = new NPC("Hardcore", new Location(Bukkit.getWorld("world"), 13.5, 100, -16.5, 0, 0),
//                    "ewogICJ0aW1lc3RhbXAiIDogMTYxMTk0MTgxMTM1MiwKICAicHJvZmlsZUlkIiA6ICJmMGIzYmRkMjEwNDg0Y2VlYjZhNTQyYmZiOGEyNTdiMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJBbm9uaW1ZVFQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGFhZWU2OGQzYjQ5OGViNzUzZjc1MzQ1ODEyNDk1OGYxN2E4OTc3MDNhMjNkODdlYmEzZDM0NzFhNTg1NDU0NCIKICAgIH0KICB9Cn0=",
//                    "LdexA+MdBtpNFdyeTUnQv4feYDrvntlFXiMirBNO/oCbiGANc8j5OGVe6j8xKhYHPftlUEYKCHJsCaz9tMhXVssXqtkD3mhw7DlkCrrq3CWcZB79DjBSQbhvjFnHu2MtLZF99D1F4HcMe55LrCpSnzK32PZO6o46oi4N/9fjmTsPATemmMQICVra9jPw424hp34cYIvwlYeQCEz9p2CVvjIpLqyHyBnexD0+IXtFA4HpdArD9YN53vlGKQF+s/I5elYbbhwVETSDkpPlCJQ2zQAiJABF24oiZM12lkv4TJ/8RMoz+AiAzVSjZbC2YoDDbHVIqA88CTThgUer5CoeqFDzx6+7RLu+1djsfD+RRU5Sp+KQ84ut8JBFg77jtnKsQbYbybbLkbZssQz/yVFxFLWNwWS3oywluu8U9rB8TXp+ED2DAbUdEm4EvwWYwENd4F0evnt754kBq5cQQnjsNsvlvcQe2d5J0VZhYIHG4Je9d1qbjRBqbOX7xfhS1wCHS40jHoZCeFPwpXdqaC0AuM4HCCegzDAYerktkXh1hAvNSTN/JRfOCEmeuHhXpEjgP7wAn5VIbJkD03OIbMhb8+Emvsl0mQxzDCyrWq/TCIP0qQ9468gzlqTR6xTWjWuOFVSxlOEm3rfRn+gazKshr38N3IgHQ4KzrXFn6sv6Ge0=",
//                    "&6&lHARDCORE SURVIVAL", "&eThere are 0 players online", "&7&o(( Right Click to join this server ))");
//
//            /* https://mineskin.org/256297055 */
//            NPC survivalGamesNPC = new NPC("SG", new Location(Bukkit.getWorld("world"), -18.5, 100, -6.5, 0, 0),
//                    "ewogICJ0aW1lc3RhbXAiIDogMTYxMjY4MjIwODg0MiwKICAicHJvZmlsZUlkIiA6ICJkZTE0MGFmM2NmMjM0ZmM0OTJiZTE3M2Y2NjA3MzViYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTUlRlYW0iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2RhMDViZmI5NDAzN2MxMzI2NGI1ZTQ2MGQwN2ZjMTE5ZTg1Y2QzNmNiOTUyYjc0NzNhYWY2Y2JhNWFlZjNkOSIKICAgIH0KICB9Cn0=",
//                    "KLcm2ZFUHc5K9AVFe7XfVcdpJGL3eM+yPJyQFIxGPhpNcKaas/oSuO6q0QuCsS85gy1oMLGnXYgg3t6+pwHlZLPNFOKaKBu3B9537+/KuPXsf/jrNCtl1L8KcbalLdC4r985K28Wm6L93DFgAr9GubFGX98ffokkXUEekpuKnzstY2Fc67Uve0enaho06rfDNLT7MPATIokUTybSnwBmVFCzQhMmUddU5rlCNhTC/6QMvuU+8LsL5OHuLMb/SHkcXo4R7rRd2ykb2dcD8GpSJeiXcp4uyO7Hl7a/8NMX6Ek4rnIzcqPjrolBWFWp4alOeE4SjlR8HbET7Q2b4v8/yB55odKKUXX2tJAODZHzqV+d2NH3c305MlD6q6uRx5cA57Iz2x2qO8VzkkqQUUQyY7GtIPKwJzwz+MUgffMAZFLdBuxvDOg/pe76qZv1IyTZ79btNUU27igKINLYYo+ROdrBMqnuxhKkJMp1WBIFtgiHyYCJk1fBnwUq5lqsDuECEn3CLf7Cgp+vP59PlzFigWxL0OV245h+f8nRhwEXWGQ+EBLnqloXjYnd882MO1Nmn+ff3n1zE/ercPfaMXes6AKofd826VtRWUomkLE2ogQW+XDEg6w3EJhD0oss8BZXex/GekB8w0j6rCqQJdxPwZjz1b4HdxiDBlOaiNe7p9Q=",
//                    "&6&lSURVIVAL GAMES", "&eThere are 0 players online", "&7&o(( Right Click to join this server ))");
//
//            /* https://mineskin.org/8451 */
//            NPC woolWarsNPC = new NPC("WoolWars", new Location(Bukkit.getWorld("world"), 19.5, 100, -6.5, 0, 0),
//                    "eyJ0aW1lc3RhbXAiOjE0ODgwNTMxMjM5NzAsInByb2ZpbGVJZCI6ImIwZDRiMjhiYzFkNzQ4ODlhZjBlODY2MWNlZTk2YWFiIiwicHJvZmlsZU5hbWUiOiJZZWxlaGEiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzUwNmMzMTdiNTgxNWE5OTU2OWFjOGUxZWE3Zjg1NjJlOWI2NTJjZGFjNWIzZDQxNzk0MmNlNTZhMDNiMTMifX19",
//                    "pDXlRyF1jB/+yRGcsxTeGo1h4sGnKBJc1sy2hKaTS0QWUHY4L3na+WRdL6xA4ZTnPtdODasYOVoM+NkeIReWjDQ2XNjfAbXMxdMkX9UvlXLrJXEgX5uochxWmKnfaMml2kALnaWLg1oho7fKFK1d8EcX2+k20AugVCLr0QK61X2eUa+WRKge4jcVKPDiLW17QEuxSOsdzb/QJWqoEwpPmfADw54VTM5QlNNximVWn8n8AjzBv5AUpKMAMxy6PmBRVwG2jYMmeKrVCAiL9e7u0+u2LmOw0poN8hcEVPcjanbD7r8/a6FoFOuepQ3IiQSwE3bsCCELXrv/dm8/Qv3kWD/M22sBE30R4Lwmd2VnhKnaZcDzHbePZe1M4qPq/gcPpgcH3/JywdQ1gh1SS9tHLB5Fc3E8d2/cNhzdzLW6F5wxIZIoOaHx+nlhZp2ZCwMUsBhRazanYmKDbyNHcbFO4AAIoGDLqoHTW4CjlXvcN267UHjLx51dSbCLE+4qooUayaxaNme1iEUghvyu0W6V3ypdQV5vWQApY3h3mZaToiM2CvKtoUkRiLxCrVptSupK/zDesdqGzktrR56Cm1aWC/8xW9B0tLFYaGiNZpCxyaO8e5vl1nzMj91PK1aEcvIcDnac71BgJVjYJZa2gArUM9GUFTUVsEdfsjGtjbzKDxk=",
//                    "&6&lWOOL WARS", "&eThere are 0 players online", "&7&o(( Right Click to join this server ))");
//
//            /* https://mineskin.org/415652 */
//            NPC blockSumoNPC = new NPC("BlockSumo", new Location(Bukkit.getWorld("world"), 19.5, 100, 7.5, 0, 0),
//                    "eyJ0aW1lc3RhbXAiOjE1NDQzMjc4MzYxNjAsInByb2ZpbGVJZCI6ImVmYWY1NzU3NzgxZTQ3YWViYzE0Y2Q4MmM5MWM3ZjgyIiwicHJvZmlsZU5hbWUiOiJNaW5lU2tpbiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTU2NTQ1Nzk3NTlmYmU5NThkOTIyMmMyYTA3ODJiM2YzOGNkNDJiNGM3ZDg3ZmIxNzYzN2QxZDI3NmE5NDhmYyJ9fX0=",
//                    "q89Aj/7Cr6z3+H+VKGjWDz2BNxnmtaKQV9Kpfhww6tsEGxjL8BqBEs9IBseuCI5fgIHNn6dKCnPnbIELcrkZBsy3adzM/3pYtKrC9FThItXJ9VwVAMb1vSX4HwlbhF8sK693WsSEyZg0qInkxmbUB6bvVltVoV0zx5ImkWroPsuBkr1bTlW/ZCuwZyrNwwJ3fiJ/IRcVDKZuoBPyLg4/kL3tz/AKY3C/b8dkbD6cEDNiomjdZT6x92I44+UkciZWUgeiNptNKhU9XLms21C1g/21BKKcLMU85TLqC3Oq/5KEljZ6aW3DVHgdQ4L8qBeAwKw2lzYKIHuUxE6csGbzdjyhNyOexkwLH0NwwWkaiSrj+LU4rwePQz71eTGNZrSBMtJ2qDGRN4RjZ0ipl2nvcRTspL7hU91cnQ++35ATwGY/WhLVPrNLxZhbPLp4Z3d6Nd2+s1wVFRQ0pUTFuO3FMYt7XWkCrZFLaIz78w2fcujmvCq1djXHaRe5Xkvk8UXzkeDO19ZFmppZqPZuLvCGriNDeIDx2prlrPfBgNxre2ogRos93AsNBrC3VfgLaHeA19TQYhtTyjyLgkt9cLy74z0WA9D7fez5/hJMgSI1Xv6hd62qeEQG1bSfiFRzeFXUXmREOI18ejwJS4WKbQzW12nH9Lhs5rX6wSyzFRt/xjw=",
//                    "&6&lBLOCK SUMO", "&eThere are 0 players online", "&7&o(( Right Click to join this server ))");
//
//            /* https://mineskin.org/756177010 */
//            NPC partyGamesNPC = new NPC("PartyGames", new Location(Bukkit.getWorld("world"), -18.5, 100, 7.5, 0, 0),
//                    "ewogICJ0aW1lc3RhbXAiIDogMTYwOTI1ODc5NjYzNywKICAicHJvZmlsZUlkIiA6ICJmMTBiYzMxNTI2OTM0YWEwYWNkYjViMDVlMjA2NWY5YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJQYXJ0eSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82OTBhYjdiMmZiYzkwODFkNzc2MWI3NjNkODRkYjcxYWU4YTkxZTFmYjRlNjdlN2VmOTI0OWFkMzFkY2UwNjc0IiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=",
//                    "c2EJqf6U8fphMbBY1XHb4MlYdm4suMtlYgOg5qRK8/D17iT9ARVquHeUAlqr7r9bR+p0MrQnNlDZXHjaohZy9GLox3AXn4X8v49GqgEJbwfsCwZz85+dC0owW47ND+tLc5x5asNl4otKS2/dBznWn6j/LEaB42nAKx2pgws0bwsatCJRoRSAyDuvW8n6SU7sql9M4bIMCRDNAGXoKuVu/4xD+IsoiNW+6l1bL8AnOlqXv6bbomYeoyqBxTK266Cxb4BOVJJo0xn2GzSFlotqYoEGHvDIaHaQ7NgWIJAeH10nPr2LitLVvIwkodn1MlTaqqyzDLSg8OrLq4pGLatPnd2PmcHxMzmEz00Uchd1x1ALnSRQ4n7xwe5W9SxV5aDRddRANA0ztxpxivumRts5hqWK5tetf426tiTFJzDjg0TYjYYGFgMUUrtQGX1dhe2I3AM7uxkUzBJoX0agfvzdZVPDN+8awPORgBL/p8ZY4dtWCQhsMRZTtgRf6FX8UOKM3XWWVTxMh5/xMgr4RON4Fxv+k/lmxIKpVHem5z8WU23+Q3UkthbgGt20YNxe2dT+B2mrLjplFIqyFIqXEoX3h/FLxn6ttub6Kv6GwMLxqxcSYZxIbD/GpYN3bb/QD6PI1KqIKlovd4UvizckKDzaZW3Db88lwkgBR2oLpfFSJic=",
//                    "&6&lPARTY GAMES", "&eThere are 0 players online", "&7&o(( Right Click to join this server ))");
//
//            Arrays.asList(new Location(Bukkit.getWorld("world"), -12.5, 100, 17.5, 0, 0),
//                    new Location(Bukkit.getWorld("world"), 0.5, 100, 21.5, 0, 0),
//                    new Location(Bukkit.getWorld("world"), 13.5, 100, 17.5, 0, 0)
//            ).forEach(location -> {
//                /* https://mineskin.org/2006229870 */
//                try {
//                    Hub.npcs.add(new NPC("Unknown", location,
//                            ,
//
//                            "&6&lUNKNOWN REALM", "&eServer is coming soon", "&7&o(( Right Click to join this server ))"));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//            Hub.npcs.addAll(Arrays.asList(survivalNPC, hardCoreNPC, survivalGamesNPC, woolWarsNPC, partyGamesNPC, blockSumoNPC));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // String server, Location location, String skin, String signature, Hologram hologram

        World world = Bukkit.getWorld("world");

        Location parkourLocation = new Location(world, -7.5, 69, -19.5);
        Location survivalLocation = new Location(world, -3.5, 69, -19.5);
        Location skyBlockLocation = new Location(world, 0.5, 69, -19.5);
        Location practiceLocation = new Location(world, 4.5, 69, -19.5);
        Location unknownLocation = new Location(world, 8.5, 69, -19.5);
        Location bitCrackLocation = new Location(world, -19.5, 69, 6.5);
        Location oHateLocation = new Location(world, -19.5, 69, 2.5);
        Location customLocation = new Location(world, -19.5, 69, -1.5);
        Location freakingChickenLocation = new Location(world, -19.5, 69, -5.5);

        CustomLib.getCustomLib().getNpcs().addAll(
                Arrays.asList(
                        new NPC(
                                Collections.singletonMap("server", "Parkour"),
                                parkourLocation,
                                "ewogICJ0aW1lc3RhbXAiIDogMTYxMjY4MzEzNDM5OCwKICAicHJvZmlsZUlkIiA6ICJmNThkZWJkNTlmNTA0MjIyOGY2MDIyMjExZDRjMTQwYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ1bnZlbnRpdmV0YWxlbnQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNlOTRkM2M2NzZiYzI1Y2QxNDgzNjU2NTlmYWJhMDYyMTMyODk2MDMzNWRkYjNiODYzNTA4MDNjNmViNDg1MSIKICAgIH0KICB9Cn0=",
                                "eH2EmqrEc+LBU8GvgqBtFc1BpKiE6y/QmveeBbmcOt9xN2dbKGCVjLKapY1yrJJuPpLYg/4Y9CxPfgB63rPj+d8MwK77f5e2Ys5ake0lcXLl87u6xWyur7ignXdqHZfg5pm+v0YUgbj+3RgecxA5fKOICux3ybXcHHSrL0Ba4sjXNpzFINHxeW7D571vMfKUTuEwHjz6gVrtGqAYJfLol2n4MyEEkqlJAI56ErnhDPbWQSXsGGL+6zwjGWqgRFkL2RFNJmkeS9pz/QBr+0ECNIakBaLzHeTCR5DhOph34OQeamwGjJJUoXD0f8UA7SoSjKQFal8lg2IhM7cEOi/xmFCqrOW1sHAmdubDjitB34q7tuUIsYyslHT3enn+G+s1nciFMZ/o+7V09r0r5Hl3Z+VGMfIuTre/e9lEQID9gEz2yEnujP0HiDOP/6944cOeuw5SO45Yqtrgu0nHswMMUALC3QNAi2c07dSfaWxAPmhv3VpsG4e2q/FCa1YK70KZ9g1qUcJvfkZvSdwAkh+mTarERnFT3LLUR+WU6XNarLBtG1HCzj7QeyqY/+DDK/ILc1DAp30L+Jl8L3ejbsNLJITpA+aF/BqY4R2m8D8bj41sIR7BbHCtCvMyxFf/U33iEGVk/byrL0Ar7bccA7pQCo0iLbSiqyV0WCwnAftu6i8=",
                                new Hologram(parkourLocation, false).append(HologramTextComponent.empty())
                        ),
                        new NPC(
                                Collections.singletonMap("server", "Survival"),
                                survivalLocation,
                                "ewogICJ0aW1lc3RhbXAiIDogMTYxMjY4MzEzNDM5OCwKICAicHJvZmlsZUlkIiA6ICJmNThkZWJkNTlmNTA0MjIyOGY2MDIyMjExZDRjMTQwYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ1bnZlbnRpdmV0YWxlbnQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNlOTRkM2M2NzZiYzI1Y2QxNDgzNjU2NTlmYWJhMDYyMTMyODk2MDMzNWRkYjNiODYzNTA4MDNjNmViNDg1MSIKICAgIH0KICB9Cn0=",
                                "eH2EmqrEc+LBU8GvgqBtFc1BpKiE6y/QmveeBbmcOt9xN2dbKGCVjLKapY1yrJJuPpLYg/4Y9CxPfgB63rPj+d8MwK77f5e2Ys5ake0lcXLl87u6xWyur7ignXdqHZfg5pm+v0YUgbj+3RgecxA5fKOICux3ybXcHHSrL0Ba4sjXNpzFINHxeW7D571vMfKUTuEwHjz6gVrtGqAYJfLol2n4MyEEkqlJAI56ErnhDPbWQSXsGGL+6zwjGWqgRFkL2RFNJmkeS9pz/QBr+0ECNIakBaLzHeTCR5DhOph34OQeamwGjJJUoXD0f8UA7SoSjKQFal8lg2IhM7cEOi/xmFCqrOW1sHAmdubDjitB34q7tuUIsYyslHT3enn+G+s1nciFMZ/o+7V09r0r5Hl3Z+VGMfIuTre/e9lEQID9gEz2yEnujP0HiDOP/6944cOeuw5SO45Yqtrgu0nHswMMUALC3QNAi2c07dSfaWxAPmhv3VpsG4e2q/FCa1YK70KZ9g1qUcJvfkZvSdwAkh+mTarERnFT3LLUR+WU6XNarLBtG1HCzj7QeyqY/+DDK/ILc1DAp30L+Jl8L3ejbsNLJITpA+aF/BqY4R2m8D8bj41sIR7BbHCtCvMyxFf/U33iEGVk/byrL0Ar7bccA7pQCo0iLbSiqyV0WCwnAftu6i8=",
                                new Hologram(survivalLocation, false).append(HologramTextComponent.empty())
                        ),
                        new NPC(
                                Collections.singletonMap("server", "SkyBlock"),
                                skyBlockLocation,
                                "ewogICJ0aW1lc3RhbXAiIDogMTYxMjY4MzEzNDM5OCwKICAicHJvZmlsZUlkIiA6ICJmNThkZWJkNTlmNTA0MjIyOGY2MDIyMjExZDRjMTQwYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ1bnZlbnRpdmV0YWxlbnQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNlOTRkM2M2NzZiYzI1Y2QxNDgzNjU2NTlmYWJhMDYyMTMyODk2MDMzNWRkYjNiODYzNTA4MDNjNmViNDg1MSIKICAgIH0KICB9Cn0=",
                                "eH2EmqrEc+LBU8GvgqBtFc1BpKiE6y/QmveeBbmcOt9xN2dbKGCVjLKapY1yrJJuPpLYg/4Y9CxPfgB63rPj+d8MwK77f5e2Ys5ake0lcXLl87u6xWyur7ignXdqHZfg5pm+v0YUgbj+3RgecxA5fKOICux3ybXcHHSrL0Ba4sjXNpzFINHxeW7D571vMfKUTuEwHjz6gVrtGqAYJfLol2n4MyEEkqlJAI56ErnhDPbWQSXsGGL+6zwjGWqgRFkL2RFNJmkeS9pz/QBr+0ECNIakBaLzHeTCR5DhOph34OQeamwGjJJUoXD0f8UA7SoSjKQFal8lg2IhM7cEOi/xmFCqrOW1sHAmdubDjitB34q7tuUIsYyslHT3enn+G+s1nciFMZ/o+7V09r0r5Hl3Z+VGMfIuTre/e9lEQID9gEz2yEnujP0HiDOP/6944cOeuw5SO45Yqtrgu0nHswMMUALC3QNAi2c07dSfaWxAPmhv3VpsG4e2q/FCa1YK70KZ9g1qUcJvfkZvSdwAkh+mTarERnFT3LLUR+WU6XNarLBtG1HCzj7QeyqY/+DDK/ILc1DAp30L+Jl8L3ejbsNLJITpA+aF/BqY4R2m8D8bj41sIR7BbHCtCvMyxFf/U33iEGVk/byrL0Ar7bccA7pQCo0iLbSiqyV0WCwnAftu6i8=",
                                new Hologram(skyBlockLocation, false).append(HologramTextComponent.empty())
                        ),
                        new NPC(
                                Collections.singletonMap("server", "Practice"),
                                practiceLocation,
                                "ewogICJ0aW1lc3RhbXAiIDogMTYxMjY4MzEzNDM5OCwKICAicHJvZmlsZUlkIiA6ICJmNThkZWJkNTlmNTA0MjIyOGY2MDIyMjExZDRjMTQwYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ1bnZlbnRpdmV0YWxlbnQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNlOTRkM2M2NzZiYzI1Y2QxNDgzNjU2NTlmYWJhMDYyMTMyODk2MDMzNWRkYjNiODYzNTA4MDNjNmViNDg1MSIKICAgIH0KICB9Cn0=",
                                "eH2EmqrEc+LBU8GvgqBtFc1BpKiE6y/QmveeBbmcOt9xN2dbKGCVjLKapY1yrJJuPpLYg/4Y9CxPfgB63rPj+d8MwK77f5e2Ys5ake0lcXLl87u6xWyur7ignXdqHZfg5pm+v0YUgbj+3RgecxA5fKOICux3ybXcHHSrL0Ba4sjXNpzFINHxeW7D571vMfKUTuEwHjz6gVrtGqAYJfLol2n4MyEEkqlJAI56ErnhDPbWQSXsGGL+6zwjGWqgRFkL2RFNJmkeS9pz/QBr+0ECNIakBaLzHeTCR5DhOph34OQeamwGjJJUoXD0f8UA7SoSjKQFal8lg2IhM7cEOi/xmFCqrOW1sHAmdubDjitB34q7tuUIsYyslHT3enn+G+s1nciFMZ/o+7V09r0r5Hl3Z+VGMfIuTre/e9lEQID9gEz2yEnujP0HiDOP/6944cOeuw5SO45Yqtrgu0nHswMMUALC3QNAi2c07dSfaWxAPmhv3VpsG4e2q/FCa1YK70KZ9g1qUcJvfkZvSdwAkh+mTarERnFT3LLUR+WU6XNarLBtG1HCzj7QeyqY/+DDK/ILc1DAp30L+Jl8L3ejbsNLJITpA+aF/BqY4R2m8D8bj41sIR7BbHCtCvMyxFf/U33iEGVk/byrL0Ar7bccA7pQCo0iLbSiqyV0WCwnAftu6i8=",
                                new Hologram(practiceLocation, false).append(HologramTextComponent.empty())
                        ),
                        new NPC(
                                Collections.singletonMap("server", "Unknown"),
                                unknownLocation,
                                "ewogICJ0aW1lc3RhbXAiIDogMTYxMjY4MzEzNDM5OCwKICAicHJvZmlsZUlkIiA6ICJmNThkZWJkNTlmNTA0MjIyOGY2MDIyMjExZDRjMTQwYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ1bnZlbnRpdmV0YWxlbnQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNlOTRkM2M2NzZiYzI1Y2QxNDgzNjU2NTlmYWJhMDYyMTMyODk2MDMzNWRkYjNiODYzNTA4MDNjNmViNDg1MSIKICAgIH0KICB9Cn0=",
                                "eH2EmqrEc+LBU8GvgqBtFc1BpKiE6y/QmveeBbmcOt9xN2dbKGCVjLKapY1yrJJuPpLYg/4Y9CxPfgB63rPj+d8MwK77f5e2Ys5ake0lcXLl87u6xWyur7ignXdqHZfg5pm+v0YUgbj+3RgecxA5fKOICux3ybXcHHSrL0Ba4sjXNpzFINHxeW7D571vMfKUTuEwHjz6gVrtGqAYJfLol2n4MyEEkqlJAI56ErnhDPbWQSXsGGL+6zwjGWqgRFkL2RFNJmkeS9pz/QBr+0ECNIakBaLzHeTCR5DhOph34OQeamwGjJJUoXD0f8UA7SoSjKQFal8lg2IhM7cEOi/xmFCqrOW1sHAmdubDjitB34q7tuUIsYyslHT3enn+G+s1nciFMZ/o+7V09r0r5Hl3Z+VGMfIuTre/e9lEQID9gEz2yEnujP0HiDOP/6944cOeuw5SO45Yqtrgu0nHswMMUALC3QNAi2c07dSfaWxAPmhv3VpsG4e2q/FCa1YK70KZ9g1qUcJvfkZvSdwAkh+mTarERnFT3LLUR+WU6XNarLBtG1HCzj7QeyqY/+DDK/ILc1DAp30L+Jl8L3ejbsNLJITpA+aF/BqY4R2m8D8bj41sIR7BbHCtCvMyxFf/U33iEGVk/byrL0Ar7bccA7pQCo0iLbSiqyV0WCwnAftu6i8=",
                                new Hologram(unknownLocation, false).append(HologramTextComponent.empty())
                        ),
                        new NPC(
                                Collections.emptyMap(),
                                bitCrackLocation,
                                "ewogICJ0aW1lc3RhbXAiIDogMTYyNzA2ODAxOTY0NywKICAicHJvZmlsZUlkIiA6ICI5MTEyMWQ4ODhhMDc0YmVmYmM3OGM4MDQ4NGUxYjZiNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJCeXRlQ3JhY2siLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTk3ZjcyMzk4ZmFiOTA4OWViNjZkMmQwNDMxZjllNDM5YTM5ZTYyZDI2MzU3NWFiMTNlYzdiZmM1MmQ2NzgyNSIKICAgIH0KICB9Cn0=",
                                "o0bGG4WJGAe2QHWVNPLvtsM29kAEjpbOEOew1bgC+0ebl1ohfG0+JyD2fMCSstr4DAwrjJ02fO28w+lzZ376z1troQlx/pqZN6HZXabUKn/zhWI9dn8FqZ7gXO/SDLZk9aWfkfLeA4xZPtRNSDI9hGQNBYgN/993L05u1YGMLFg4wBxObvh8I1lU+CPi5l1xRynkqsdGL2F7jokRxajlQi7nD3DkKQa7N++PpLx98m2fpkkZIOvBTrYbm+GTm40uKUCPs/kHoIDlvza0N6QBuhvfxdJZ0QDBNI9Mgn+w+vjUcjZ3k4JIksfG+/PK5Z6BmyKZXrKAMR3GzqL9JDa6gktVkqOTddqT3yrFW0dZeO+6Jc8HMS4AyY9uEbYdIYObbYVwaMcNf9uVWlIP/Q7OwIUK3DgMwCOFxWTffMr+q34M2FRSESksuInLCQZGZjMYIcGuesspxiPlmCfNqrs0apEm6tam5Sarw1Z7bhU0xFLBIqesOEkNpk9SbXb4i/VAb8uZfG2AA5UGYwdKknUB2/QJNFvTrCEo4/xqc5J3Xdyse/Fn61Tk/srpfnrCIlPEhWue6EIIUOrne/MBbWkWUGIvN9+SPAsVjhBmgNxt92N6NJjhkBKvpaVMfBO+V/Y42OCOriGvPCVsYHAae/F1mC9mBeCT6zKLvqJZCPTjFtA=",
                                new Hologram(bitCrackLocation, false).append(new HologramTextComponent("&8[&4Owner&8] &4ByteCrack"))
                        ),
                        new NPC(
                                Collections.emptyMap(),
                                oHateLocation,
                                "ewogICJ0aW1lc3RhbXAiIDogMTYxMTgwOTQ3NzAzNCwKICAicHJvZmlsZUlkIiA6ICIyMzljODU3MzA2NzU0YTc4ODJmM2QxNzhjNzJkNWRjOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJvSGF0ZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hOWRiNDQxMmQzZjlhMDdkODU3NDg3MDk0ZjdhYzBiNThjZDRkNWUyMzE5YzQ1ZDExMDJiNDg0ZjlhNWMyNDllIgogICAgfQogIH0KfQ==",
                                "qc9mpWyoQacVTWhkUaNa8TlBbGqG7YQO7je7YNe9N5zW0LRr/7bhqJIAGxoa5NE4MytQy9cZrQwd0yll8bR0+zXtso9ajRIATwCrd+dBMaKy550X8Gca+x3NDLboEllQjrGSTQRVOt+NW9NetNZAzVaUI3D4gfSoNgtgLutFxUBhoeT+iUcmQQrFn16OizLgRP+6bBi7pdxf2f3SAJbtMNETWubOhA/J+XF2iQrr9QfE+tVK+aRZvNNwT5L9FtpSxO4FmRvZkf5JH65emIruQmI6iEfCzeoc+BCgaW1s+NAHbbGfHOkStsrvL6BcRfTLeleGfDZQQ4JG7k+LJQqzYdkBYjiOmntdvZVX7S7U58pnGL6B3wDCVrhCMcMhiZwfgncOLLUQ2pazcX9zGHZ5FWkCVpUrwqdY6XMyhsbVTsQ7+CcnCGp+10YeBRc/fqJ7mdQ38cdDaBzS6kXLY6/yeUWz42RU7HDRhn+XccaA5B6HPIU2LTt0L0Eu6bkcQ2erxDsUPpqe2+qrYz4Dg7UP+k1xlm079/L7WWWzmGoEwsz+/txbAyNtqRK34DhIQfkJJtNH1xhdTyjMz8DQLVJbCxJPigrOUp8XfOO7lelzZUoj5b8hBM7NHW04LDUW1WQSlN/MfveknIFcuC0BMJeMtgCFPqBE53zyAZF2Twm5bUI=",
                                new Hologram(oHateLocation, false).append(new HologramTextComponent("&8[&4Owner&8] &4oHate"))
                        ),
                        new NPC(
                                Collections.emptyMap(),
                                customLocation,
                                "ewogICJ0aW1lc3RhbXAiIDogMTYyNTAyNTAxMDM0NiwKICAicHJvZmlsZUlkIiA6ICJhMjEwZGE3YmU2NzE0MmYzYmY1YmVlZWMwMjgyYjVjYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDdXN0b20iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmRiZmE4MzYzYWU1N2VhNTE0NzUyMjk3MGVlNWExYTlkMmE3NmI4MGU3N2NiMTE3MTg1YjE0OWMxNDQwZTU4MCIKICAgIH0KICB9Cn0=",
                                "JEAVsGx4NlXsUoRB4aWoduLHrmJPNkoDl0/UrRNsWezd6yvxnZmxZcIiDQm45TH7rJEvv8cGoQoiW7azUtpOExZsl4zFXIQ0paLa3n4eDImVOAhPh55Y2aL55Bcr+qn61blQq8qSoBcRIajXUbuzeSDa++A6jOZrD93HLOarUhlwwyEqXsMwO6b84GSF6qS6zkif2qyiuQuJP1zh1mh/nQU4WERUiIGJ2IC2O6v3U2byOI3UiFti4j2/i8fNqBpMFuzbfAW2my55g5s8bLmwzlFiUdfJvi2+ZX+6Y9RQYELiv/qpxU2CUu6DacgZEiqpL+xVvYG0G0HLTVASzdKGa2QJwpRZ3L+1ZOQABzsFHSSQalPQJgdYqYR6KrUGoupFwRLQel58Bhvta2bZTFfTbUUpwTAuWG1W0DsJ+r/wqolR7a861wn5bbaCNUbXHuTOPnnp1Q5zSAB44Gqixg9LhlbRazk33UQp4HMfrdQmERXi6FX4gf/H2yZCg78zU5LlwgXhIoARM0HCXdmpdecSc4phjvBNzXkeYNOznfIcnPMXHaC/77kZJVSZr43pZBu1VKsLsXEPJHglJh3jeTt68TGA/Y0PdFfo98Pl0Kzq+P0tGUUFj8ycmCyKXEUDMWCIvfRg7R57RG+0jCMg1XB7MMZQ/NBtoIT4a28qoHS6jy8=",
                                new Hologram(customLocation, false).append(new HologramTextComponent("&8[&4Owner&8] &4Custom"))
                        ),
                        new NPC(
                                Collections.emptyMap(),
                                freakingChickenLocation,
                                "ewogICJ0aW1lc3RhbXAiIDogMTYwOTY5NjQ5MDA5OCwKICAicHJvZmlsZUlkIiA6ICI5ZDQyNWFiOGFmZjg0MGU1OWM3NzUzZjc5Mjg5YjMyZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUb21wa2luNDIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Y1MTZiYjVkZmE5NmZkMjczYTgyNzdlMmE3ZDVmNjBlMTkwZGVkYzc1MDI5Y2YyMWIzZmNhZDE2Y2M0NDhmZiIKICAgIH0KICB9Cn0=",
                                "teN3Ws6iCVk2MGDjwQpupnHNnqdPTvxwMdB6y4wi92NoE8sN0IxuAzLk7nA+6V4xRKSX/4kK4sqAOG5+6gLzbnTTcymqbIVkXtheODS3Ynn7NTNqT9jIFfycnpHIyPcMwX50lyDVbsTrvukbkZ/e2GelbKr5sXLKMmBhXYBrmyTSYpNpAdKqe1mAtz19BEvpMn4pBMqjywVe3bupzIucbdTol6ifprqI5TE4cfPrLWztNfz3R4ZLk4C6wUWd9qBMYVO7C74X3leZmct3n3LbMJm84jI+PIutOaSFrkjictfyyKwQQiO92l1GOgefQxzN7R1PlT4tbn5oG1YBHrIV1uzw24Sj4esZrDzlA+gwUPiRWOnDONkadfgE71Z9HhHKTIv/mHgNlSfrAB07hsfvqMZfFGuUjT1qLiijaOCU+2xlOeKGG0eQMSuGtVuiU+y1W3zmso+I/o43jEw4h62zGABrnOBgtgwK9+miQCdeXQL8KJFsHmvr7BfF/H6Wg2+sCfLW3xanNWRiPXxx5gAUaqxLJ3cScgb21j/Dj2Dv6yx4SwuImItdGgYcFWvPMHQluFsup5JMJfekmS7kb1SIcCWoV79uk/5U3XTn7tbIr1XEMVyeWnfvSwS5AyfwdQZHYGfg9VHq9sBhgtij4JYJqb3xZeRIPdPC3jArzeDhpDs=",
                                new Hologram(freakingChickenLocation, false).append(new HologramTextComponent("&8[&4Owner&8] &4FreakingChicken"))
                        )
                )
        );

        hub.setSpawn(new Location(world, 0.5, 74, 0.5, 180, 0));

        world.setStorm(false);
        world.setThundering(false);
        world.setWeatherDuration(Integer.MAX_VALUE);

        Arrays.asList(
                GameRule.ANNOUNCE_ADVANCEMENTS,
                GameRule.COMMAND_BLOCK_OUTPUT,
                GameRule.DO_DAYLIGHT_CYCLE,
                GameRule.DO_ENTITY_DROPS,
                GameRule.DO_FIRE_TICK,
                GameRule.DO_MOB_SPAWNING,
                GameRule.SPECTATORS_GENERATE_CHUNKS,
                GameRule.DROWNING_DAMAGE,
                GameRule.SHOW_DEATH_MESSAGES,
                GameRule.MOB_GRIEFING,
                GameRule.LOG_ADMIN_COMMANDS,
                GameRule.DO_TILE_DROPS,
                GameRule.DO_MOB_LOOT
        ).forEach(rule -> world.setGameRule(rule, false));

        Arrays.asList(
                new HubListeners(),
                new CancelledListeners()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, hub));

//        new BukkitRunnable() {
//            @Override
//            public void run() {
//                List<String> list = CC.translate(Arrays.asList(
//                        "&6&oDid you know? We have a Discord!\n \n&7Join our discord for crucial updates, announcements, giveaways, & more!\n \n&eminetale.com/discord",
//                        "&c&oDid you know? &6We have a Discord!\n \n&7Join our discord for crucial updates, announcements, giveaways, & more!\n \n&eminetale.com/discord",
//                        "&c&oDid you know? &6We have a Discord!\n \n&7Join our discord for crucial updates, announcements, giveaways, & more!\n \n&aminetale.com/discord"));
//                Bukkit.broadcastMessage(list.get(new Random().nextInt(list.size())));
//            }
//        }.runTaskTimer(hub, 0, 20 * 180);
    }
}
